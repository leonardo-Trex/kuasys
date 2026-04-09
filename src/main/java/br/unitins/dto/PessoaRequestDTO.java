package br.unitins.dto;

import java.time.LocalDate;

public record PessoaRequestDTO(
        Long id,
        String nome,
        String nacionalidade,
        LocalDate dataNascimento) {

}
