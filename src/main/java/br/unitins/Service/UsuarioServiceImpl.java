package br.unitins.Service;

import br.unitins.Service.interfaces.UsuarioService;
import br.unitins.model.Usuario;
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
        repository.persist(usuario);
        return usuario;
    }

    @Override
    @Transactional
    public UsuarioResponseDTO criar(UsuarioRequestDTO dto) {
        // 1) Create user in Keycloak
        UserRepresentation user = new UserRepresentation();
        user.setUsername(dto.login());
        user.setEmail(dto.email());
        user.setEnabled(true);
        user.setFirstName(dto.nome());

        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setType(CredentialRepresentation.PASSWORD);
        cred.setValue(dto.senha());
        cred.setTemporary(false);
        user.setCredentials(Collections.singletonList(cred));

        Response resp = keycloak.realm(keycloakRealm).users().create(user);
        if (resp.getStatus() != 201 && resp.getStatus() != 204) {
            throw new RuntimeException("Erro ao criar usuário no Keycloak: status=" + resp.getStatus());
        }

        // 2) Capture generated Keycloak ID
        String keycloakId = null;
        if (resp.getLocation() != null) {
            String path = resp.getLocation().getPath();
            keycloakId = path.substring(path.lastIndexOf('/') + 1);
        }
        // fallback: search by username
        if (keycloakId == null || keycloakId.isBlank()) {
            List<UserRepresentation> found = keycloak.realm(keycloakRealm).users().search(dto.login(), 0, 1);
            if (found != null && !found.isEmpty()) {
                keycloakId = found.get(0).getId();
            }
        }

        if (keycloakId == null || keycloakId.isBlank()) {
            throw new RuntimeException("Não foi possível recuperar o ID do usuário criado no Keycloak");
        }

        // 3) Persist local Usuario with keycloakId
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
        Usuario existing = findById(id);
        if (existing == null) {
            return null;
        }
        // update existing entity with DTO values
        if (dto.keycloakId() != null)
            existing.setKeycloakId(dto.keycloakId());
        if (dto.nome() != null)
            existing.setNome(dto.nome());
        if (dto.email() != null)
            existing.setEmail(dto.email());
        if (dto.cpf() != null)
            existing.setCpf(dto.cpf());
        if (dto.telefone() != null)
            existing.setTelefone(dto.telefone());

        return UsuarioMapper.toResponseDTO(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UsuarioResponseDTO buscarPorKeycloakId(String keycloakId) {
        Usuario u = repository.findByKeycloakId(keycloakId);
        return UsuarioMapper.toResponseDTO(u);
    }

    @Override
    @Transactional
    public boolean deletarPorId(Long id) {
        return repository.deleteById(id);
    }
}
