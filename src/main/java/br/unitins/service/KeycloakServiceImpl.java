package br.unitins.service;

import br.unitins.exceptions.ValidationException;
import br.unitins.model.enums.Perfil;
import br.unitins.service.interfaces.KeycloakService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class KeycloakServiceImpl implements KeycloakService {

    private static final Logger LOG = Logger.getLogger(KeycloakServiceImpl.class);

    @Inject
    Keycloak keycloak; // A IDE reclama, mas isso é resolvido em compile-time

    @ConfigProperty(name = "quarkus.keycloak.admin-client.realm", defaultValue = "kuasys-realm")
    String realm;

    private RealmResource getRealmResource() {
        return keycloak.realm(realm);
    }

    @Override
    public String cadastrarUsuario(String email, String senha, String role) {
        if (email == null || email.isBlank()) {
            throw new ValidationException("E-mail não pode ser vazio.");
        }
        if (senha == null || senha.isBlank()) {
            throw new ValidationException("Senha não pode ser vazia.");
        }

        UserRepresentation user = new UserRepresentation();
        user.setUsername(email);
        user.setEmail(email);
        user.setEnabled(true);
        user.setEmailVerified(true);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(senha);
        credential.setTemporary(false);

        user.setCredentials(Collections.singletonList(credential));

        RealmResource realmResource = getRealmResource();
        UsersResource usersResource = realmResource.users();

        try (Response response = usersResource.create(user)) {
            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                String userId = CreatedResponseUtil.getCreatedId(response);

                if (role != null && !role.isBlank()) {
                    atribuirRole(userId, role);
                }

                return userId;
            } else if (response.getStatus() == Response.Status.CONFLICT.getStatusCode()) {
                throw new ValidationException("Usuário já cadastrado no Keycloak com o e-mail fornecido.");
            } else {
                String errorMessage = response.readEntity(String.class);
                LOG.errorf("Erro ao criar usuário no Keycloak. Status: %d, Detalhes: %s", response.getStatus(), errorMessage);
                throw new ValidationException("Falha ao registrar usuário no Keycloak. Código de status: " + response.getStatus());
            }
        } catch (WebApplicationException e) {
            LOG.error("Erro na comunicação com o Keycloak ao criar usuário", e);
            throw new ValidationException("Erro de comunicação com o servidor de autenticação: " + e.getMessage());
        }
    }

    @Override
    public String cadastrarUsuario(String email, String senha, Perfil perfil) {
        String roleName = perfil != null ? perfil.getNome().toLowerCase() : null;
        return cadastrarUsuario(email, senha, roleName);
    }

    @Override
    public void atribuirRole(String keycloakId, String role) {
        if (role == null || role.isBlank()) {
            return;
        }

        RealmResource realmResource = getRealmResource();
        UserResource userResource = realmResource.users().get(keycloakId);

        RoleRepresentation roleRep;
        try {
            roleRep = realmResource.roles().get(role).toRepresentation();
        } catch (NotFoundException e) {
            try {
                roleRep = realmResource.roles().get(role.toLowerCase()).toRepresentation();
            } catch (NotFoundException ex) {
                LOG.warnf("Role '%s' não encontrada no realm '%s'.", role, realm);
                throw new ValidationException("Role '" + role + "' não foi encontrada no Keycloak.");
            }
        }

        userResource.roles().realmLevel().add(Collections.singletonList(roleRep));
    }

    @Override
    public void atualizarSenha(String keycloakId, String novaSenha) {
        if (novaSenha == null || novaSenha.isBlank()) {
            throw new ValidationException("A nova senha não pode ser vazia.");
        }

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(novaSenha);
        credential.setTemporary(false);

        RealmResource realmResource = getRealmResource();
        realmResource.users().get(keycloakId).resetPassword(credential);
    }

    @Override
    public void deletarUsuario(String keycloakId) {
        RealmResource realmResource = getRealmResource();
        try (Response response = realmResource.users().delete(keycloakId)) {
            if (response.getStatus() != Response.Status.NO_CONTENT.getStatusCode() &&
                    response.getStatus() != Response.Status.OK.getStatusCode() &&
                    response.getStatus() != Response.Status.NOT_FOUND.getStatusCode()) {
                LOG.errorf("Erro ao deletar usuário %s no Keycloak. Status: %d", keycloakId, response.getStatus());
            }
        }
    }

    @Override
    public UserRepresentation findById(String keycloakId) {
        try {
            return getRealmResource().users().get(keycloakId).toRepresentation();
        } catch (NotFoundException e) {
            return null;
        }
    }

    @Override
    public UserRepresentation findByEmail(String email) {
        List<UserRepresentation> users = getRealmResource().users().searchByEmail(email, true);
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }
}
