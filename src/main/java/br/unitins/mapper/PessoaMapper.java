package br.unitins.mapper;

import br.unitins.dto.PessoaRequestDTO;
import br.unitins.dto.PessoaResponseDTO;
import br.unitins.model.Quadrinista;

public class PessoaMapper {

    public static Quadrinista toEntity(PessoaRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Quadrinista quadrinista = new Quadrinista();

        quadrinista.setNome(dto.nome());
        quadrinista.setNacionalidade(dto.nacionalidade());
        quadrinista.setDataNascimento(dto.dataNascimento());

        return quadrinista;
    }

    public static PessoaResponseDTO toResponseDTO(Quadrinista quadrinista) {
        if (quadrinista == null) {
            return null;
        }

        return new PessoaResponseDTO(
                quadrinista.getId(),
                quadrinista.getNome(),
                quadrinista.getNacionalidade(),
                quadrinista.getDataNascimento());
    }
}