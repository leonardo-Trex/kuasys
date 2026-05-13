package br.unitins.Service;

import br.unitins.Service.interfaces.AuthService;
import br.unitins.dto.AuthRequestDTO;
import br.unitins.dto.AuthResponseDTO;
import br.unitins.exceptions.ValidationException;
import br.unitins.model.Usuario;
import br.unitins.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthServiceImpl implements AuthService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

    @Override
    public AuthResponseDTO login(AuthRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByLogin(dto.login());
        if (usuario == null || !Boolean.TRUE.equals(usuario.getAtivo())) {
            throw new ValidationException("Login ou senha inválidos [ativo-null]");
        }

        if (!hashService.verificarBcrypt(dto.senha(), usuario.getSenhaHash())) {
            throw new ValidationException("Login ou senha inválidos [bcrypt]");
        }

        String token = jwtService.gerarToken(usuario);
        return new AuthResponseDTO(token, "Bearer");
    }
}
