package br.unitins.service.interfaces;

import br.unitins.model.enums.Perfil;
import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakService {

    String cadastrarUsuario(String email, String senha, String role);

    String cadastrarUsuario(String email, String senha, Perfil perfil);

    void atribuirRole(String keycloakId, String role);

    void atualizarSenha(String keycloakId, String novaSenha);

    void deletarUsuario(String keycloakId);

    UserRepresentation findById(String keycloakId);

    UserRepresentation findByEmail(String email);
}
