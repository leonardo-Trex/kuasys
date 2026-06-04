package br.unitins.Service;

import br.unitins.Service.interfaces.UsuarioService;
import br.unitins.model.Usuario;
import br.unitins.model.enums.Perfil;
import br.unitins.repository.UsuarioRepository;
import br.unitins.dto.UsuarioRequestDTO;
import br.unitins.dto.UsuarioResponseDTO;
import br.unitins.mapper.UsuarioMapper;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Inject
    Keycloak keycloak;

    @ConfigProperty(name = "quarkus.keycloak.admin-client.realm", defaultValue = "master")
    String keycloakRealm;

    @Override
    public List<Usuario> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Usuario findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Usuario> findByNome(String nome) {
        return repository.findByNome(nome).list();
    }

    @Override
    public Usuario findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public Usuario findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional
    public Usuario create(Usuario usuario) {
        if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            throw new RuntimeException("Senha é obrigatória para criar um novo usuário");
        }

        // 1) Create user in Keycloak
        String keycloakId = criarNoKeycloak(usuario.getLogin(), usuario.getEmail(), usuario.getNome(),
                usuario.getSenha());

        // 2) Set Keycloak ID in the usuario object
        usuario.setKeycloakId(keycloakId);

        // 3) Persist to local database
        repository.persist(usuario);
        return usuario;
    }

    @Override
    @Transactional
    public UsuarioResponseDTO criar(UsuarioRequestDTO dto) {
        // 1) Create user in Keycloak and get the UUID
        String keycloakId = criarNoKeycloak(dto.login(), dto.email(), dto.nome(), dto.senha());

        // 2) Persist local Usuario with keycloakId
        Usuario u = UsuarioMapper.toEntity(dto);
        u.setKeycloakId(keycloakId);
        repository.persist(u);
        return UsuarioMapper.toResponseDTO(u);
    }

    @Override
    @Transactional
    public void update(Long id, Usuario usuario) {
        Usuario existing = findById(id);
        if (existing == null) {
            return;
        }
        // Update fields present in the current Usuario entity
        existing.setKeycloakId(usuario.getKeycloakId());
        existing.setNome(usuario.getNome());
        existing.setEmail(usuario.getEmail());
        existing.setCpf(usuario.getCpf());
        existing.setTelefone(usuario.getTelefone());
    }

    @Override
    @Transactional
    public UsuarioResponseDTO atualizarDto(Long id, UsuarioRequestDTO dto) {
        Usuario existing = repository.findById(id); // Certifique-se de usar o seu repository aqui
        if (existing == null) {
            return null;
        }

        // 1) Verificar se houve alteração de e-mail ou nome para atualizar o Keycloak
        boolean mudouEmail = dto.email() != null && !dto.email().equals(existing.getEmail());
        boolean mudouNome = dto.nome() != null && !dto.nome().equals(existing.getNome());

        // 2) Atualiza os dados no banco local (Removido o bloco do keycloakId que dava
        // erro!)
        if (dto.nome() != null)
            existing.setNome(dto.nome());
        if (dto.email() != null)
            existing.setEmail(dto.email());
        if (dto.cpf() != null)
            existing.setCpf(dto.cpf());
        if (dto.telefone() != null)
            existing.setTelefone(dto.telefone());
        if (dto.login() != null)
            existing.setLogin(dto.login());

        // Se você tiver o enum/campo de perfil e ativo na entidade:
        if (dto.perfil() != null)
            existing.setPerfil(Perfil.valueOf(dto.perfil()));
        existing.setAtivo(dto.ativo());

        // 3) Sincroniza com o Keycloak se Nome ou E-mail mudaram
        if (mudouEmail || mudouNome) {
            try {
                // Busca a representação atual do usuário lá no Keycloak
                UserRepresentation keycloakUser = keycloak.realm(keycloakRealm)
                        .users()
                        .get(existing.getKeycloakId())
                        .toRepresentation();

                if (mudouEmail)
                    keycloakUser.setEmail(dto.email());
                if (mudouNome)
                    keycloakUser.setFirstName(dto.nome());

                // Envia a atualização para o Keycloak
                keycloak.realm(keycloakRealm)
                        .users()
                        .get(existing.getKeycloakId())
                        .update(keycloakUser);

            } catch (Exception e) {
                throw new RuntimeException("Erro ao atualizar dados cadastrais no Keycloak: " + e.getMessage(), e);
            }
        }

        // O Hibernate já sincroniza o 'existing' no banco ao fim da transação
        return UsuarioMapper.toResponseDTO(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Usuario existing = repository.findById(id);
        if (existing != null) {
            // 1) Deleta do Keycloak usando o UUID salvo
            try {
                keycloak.realm(keycloakRealm).users().get(existing.getKeycloakId()).remove();
            } catch (Exception e) {
                // Logue ou trate o erro se o usuário já não existir lá
            }
            // 2) Deleta do banco local
            repository.deleteById(id);
        }
    }

    @Override
    public UsuarioResponseDTO buscarPorKeycloakId(String keycloakId) {
        Usuario u = repository.findByKeycloakId(keycloakId);
        return UsuarioMapper.toResponseDTO(u);
    }


    /**
     * Cria um usuário no Keycloak e retorna o UUID gerado
     */
    private String criarNoKeycloak(String login, String email, String nome, String senha) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(login);
        user.setEmail(email);
        user.setEnabled(true);
        user.setFirstName(nome);

        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setType(CredentialRepresentation.PASSWORD);
        cred.setValue(senha);
        cred.setTemporary(false);
        user.setCredentials(Collections.singletonList(cred));

        Response resp = keycloak.realm(keycloakRealm).users().create(user);
        if (resp.getStatus() != 201 && resp.getStatus() != 204) {
            throw new RuntimeException("Erro ao criar usuário no Keycloak: status=" + resp.getStatus());
        }

        // Capture generated Keycloak ID
        String keycloakId = null;
        if (resp.getLocation() != null) {
            String path = resp.getLocation().getPath();
            keycloakId = path.substring(path.lastIndexOf('/') + 1);
        }

        // Fallback: search by username
        if (keycloakId == null || keycloakId.isBlank()) {
            List<UserRepresentation> found = keycloak.realm(keycloakRealm).users().search(login, 0, 1);
            if (found != null && !found.isEmpty()) {
                keycloakId = found.get(0).getId();
            }
        }

        if (keycloakId == null || keycloakId.isBlank()) {
            throw new RuntimeException("Não foi possível recuperar o ID do usuário criado no Keycloak");
        }

        return keycloakId;
    }
}
