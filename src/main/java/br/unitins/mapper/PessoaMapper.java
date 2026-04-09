package br.unitins.mapper;

import br.unitins.dto.PessoaRequestDTO;
import br.unitins.dto.PessoaResponseDTO;
import br.unitins.model.Pessoa;

public class PessoaMapper {

    public static Pessoa toEntity(PessoaRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Pessoa pessoa = new Pessoa();

        pessoa.setNome(dto.nome());
        pessoa.setNacionalidade(dto.nacionalidade());
        pessoa.setDataNascimento(dto.dataNascimento());

        return pessoa;
    }

    public static PessoaResponseDTO toResponseDTO(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }

        return new PessoaResponseDTO(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getNacionalidade(),
                pessoa.getDataNascimento());
    }
}