package br.unitins.mapper;

import br.unitins.dto.endereco.EnderecoRequestDTO;
import br.unitins.dto.endereco.EnderecoResponseDTO;
import br.unitins.model.Endereco;
import br.unitins.model.Usuario;

public class EnderecoMapper {

    public static Endereco toEntity(EnderecoRequestDTO dto, Usuario usuario) {
        if (dto == null) {
            return null;
        }

        return new Endereco(
                null,
                dto.logradouro(),
                dto.numero(),
                dto.complemento(),
                dto.bairro(),
                dto.cidade(),
                dto.estado(),
                dto.cep(),
                dto.isPrincipal(),
                usuario
        );
    }

    public static EnderecoResponseDTO toResponseDTO(Endereco endereco) {
        if (endereco == null) {
            return null;
        }

        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep(),
                endereco.getIsPrincipal(),
                endereco.getUsuario() != null ? endereco.getUsuario().getId() : null
        );
    }
}
