package br.unitins.resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@jakarta.ws.rs.Path("/imagens")
@PermitAll
public class ImagemResource {

    private static final Path DIRETORIO_SEGURO = Paths.get(System.getProperty("user.home"), "kuasys", "imagens").toAbsolutePath().normalize();

    @GET
    @jakarta.ws.rs.Path("/{nomeArquivo}")
    public Response baixarImagem(@PathParam("nomeArquivo") String nomeArquivo) {
        
        try {
            // 1. Bloqueia tentativas de Directory Traversal limando caminhos relativos
            Path caminhoArquivo = DIRETORIO_SEGURO.resolve(nomeArquivo).normalize();

            // 2. Validação crucial: O caminho final do arquivo AINDA ESTÁ dentro do diretório seguro?
            if (!caminhoArquivo.startsWith(DIRETORIO_SEGURO)) {
                return Response.status(Status.FORBIDDEN).entity("Acesso negado: Tentativa de evasão de diretório.").build();
            }

            // 3. Verifica se o arquivo realmente existe e não é uma pasta
            if (!Files.exists(caminhoArquivo) || Files.isDirectory(caminhoArquivo)) {
                return Response.status(Status.NOT_FOUND).build();
            }

            // 4. Descobre o tipo real do arquivo (MIME type) dinamicamente (ex: image/png, image/jpeg)
            String contentType = Files.probeContentType(caminhoArquivo);
            
            // 5. Segurança extra: Se não for uma imagem, bloqueia o download
            if (contentType == null || !contentType.startsWith("image/")) {
                return Response.status(Status.BAD_REQUEST).entity("O arquivo solicitado não é uma imagem válida.").build();
            }

            // Retorna o arquivo com o Content-Type correto detectado dinamicamente
            return Response.ok(caminhoArquivo.toFile())
                    .type(contentType)
                    .build();

        } catch (IOException e) {
            // Log do erro (use um Logger em produção)
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}