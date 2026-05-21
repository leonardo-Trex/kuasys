// package br.unitins.Service;

// import java.util.Set;

// import br.unitins.model.Usuario;
// import io.smallrye.jwt.build.Jwt;
// import jakarta.enterprise.context.ApplicationScoped;

// @ApplicationScoped
// public class JwtService {

//       // Tempo de expiração: 24 horas em segundos
//     private static final long EXPIRACAO_SEGUNDOS = 24*3600L;

//      /**
//      * Gera um token JWT assinado para o usuario autenticado.
//      * O perfil é incluido como grupo (claim "groups"), permitindo uso com @RolesAllowed.
//      */
//     public String gerarToken(Usuario usuario) {
//         Set<String> grupos = usuario.getPerfil() != null ? Set.of(usuario.getPerfil().name()) : Set.of();
//         return Jwt.issuer("sga-api")
//                 .upn(usuario.getLogin())
//                 .groups(grupos)
//                 .expiresIn(EXPIRACAO_SEGUNDOS)
//                 .sign();
//     }
// }
