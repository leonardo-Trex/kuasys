package br.unitins.Service;

import br.unitins.Service.interfaces.UsuarioService;
import br.unitins.model.Usuario;
import br.unitins.model.enums.Perfil;
import br.unitins.repository.UsuarioRepository;
import br.unitins.dto.UsuarioRequestDTO;
import br.unitins.dto.UsuarioResponseDTO;
import br.unitins.mapper.UsuarioMapper;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RoleScopeResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
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

    // Realm onde os usuários da aplicação são gerenciados
    private static final String TARGET_REALM = "realm-kuasys";

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

        // 3) Atribui a role correspondente ao perfil no Keycloak
        if (usuario.getPerfil() != null) {
            atribuirRoleNoKeycloak(keycloakId, usuario.getPerfil());
        }

        // 4) Persist to local database
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

        // 3) Atribui a role correspondente ao perfil no Keycloak
        if (u.getPerfil() != null) {
            atribuirRoleNoKeycloak(keycloakId, u.getPerfil());
        }

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

        // Detecta mudança de perfil antes de atualizar
        Perfil perfilAntigo = existing.getPerfil();
        Perfil perfilNovo = dto.perfil() != null ? Perfil.valueOf(dto.perfil()) : perfilAntigo;
        boolean mudouPerfil = perfilNovo != null && !perfilNovo.equals(perfilAntigo);

        if (dto.perfil() != null)
            existing.setPerfil(perfilNovo);
        existing.setAtivo(dto.ativo());

        // 3) Sincroniza com o Keycloak se Nome ou E-mail mudaram
        if (mudouEmail || mudouNome) {
            try {
                UserRepresentation keycloakUser = keycloak.realm(TARGET_REALM)
                        .users()
                        .get(existing.getKeycloakId())
                        .toRepresentation();

                if (mudouEmail)
                    keycloakUser.setEmail(dto.email());
                if (mudouNome)
                    keycloakUser.setFirstName(dto.nome());

                keycloak.realm(TARGET_REALM)
                        .users()
                        .get(existing.getKeycloakId())
                        .update(keycloakUser);

            } catch (Exception e) {
                throw new RuntimeException("Erro ao atualizar dados cadastrais no Keycloak: " + e.getMessage(), e);
            }
        }

        // 4) Sincroniza a role no Keycloak se o perfil mudou
        if (mudouPerfil) {
            sincronizarRoleNoKeycloak(existing.getKeycloakId(), perfilAntigo, perfilNovo);
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
                keycloak.realm(TARGET_REALM).users().get(existing.getKeycloakId()).remove();
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

        Response resp = keycloak.realm(TARGET_REALM).users().create(user);
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
            List<UserRepresentation> found = keycloak.realm(TARGET_REALM).users().search(login, 0, 1);
            if (found != null && !found.isEmpty()) {
                keycloakId = found.get(0).getId();
            }
        }

        if (keycloakId == null || keycloakId.isBlank()) {
            throw new RuntimeException("Não foi possível recuperar o ID do usuário criado no Keycloak");
        }

        return keycloakId;
    }

    /**
     * Converte o enum Perfil para o nome da role no Keycloak.
     * Perfil.ADMIN  -> "admin"
     * Perfil.USUARIO -> "usuario"
     */
    private String getRealmRoleName(Perfil perfil) {
        return perfil.getNOME().toLowerCase();
    }

    /**
     * Atribui a realm role correspondente ao perfil do usuário no Keycloak.
     * Utilizado no momento da criação do usuário.
     */
    private void atribuirRoleNoKeycloak(String keycloakId, Perfil perfil) {
        try {
            String roleName = getRealmRoleName(perfil);

            // Busca a representação da role no realm
            RoleRepresentation role = keycloak.realm(TARGET_REALM)
                    .roles()
                    .get(roleName)
                    .toRepresentation();

            // Atribui a role ao usuário
            keycloak.realm(TARGET_REALM)
                    .users()
                    .get(keycloakId)
                    .roles()
                    .realmLevel()
                    .add(Collections.singletonList(role));

        } catch (Exception e) {
            throw new RuntimeException(
                    "Erro ao atribuir role '" + perfil.getNOME() + "' no Keycloak: " + e.getMessage(), e);
        }
    }

    /**
     * Sincroniza a role do usuário no Keycloak quando o perfil é alterado.
     * Remove a role antiga e adiciona a nova.
     */
    private void sincronizarRoleNoKeycloak(String keycloakId, Perfil perfilAntigo, Perfil perfilNovo) {
        try {
            RoleScopeResource roleScopeResource = keycloak.realm(TARGET_REALM)
                    .users()
                    .get(keycloakId)
                    .roles()
                    .realmLevel();

            // Remove a role antiga (se existir)
            if (perfilAntigo != null) {
                String roleAntigaName = getRealmRoleName(perfilAntigo);
                RoleRepresentation roleAntiga = keycloak.realm(TARGET_REALM)
                        .roles()
                        .get(roleAntigaName)
                        .toRepresentation();
                roleScopeResource.remove(Collections.singletonList(roleAntiga));
            }

            // Adiciona a nova role
            String roleNovaName = getRealmRoleName(perfilNovo);
            RoleRepresentation roleNova = keycloak.realm(TARGET_REALM)
                    .roles()
                    .get(roleNovaName)
                    .toRepresentation();
            roleScopeResource.add(Collections.singletonList(roleNova));

        } catch (Exception e) {
            throw new RuntimeException(
                    "Erro ao sincronizar roles no Keycloak: " + e.getMessage(), e);
        }
    }
}
