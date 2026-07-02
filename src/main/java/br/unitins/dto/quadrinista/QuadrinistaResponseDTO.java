package br.unitins.dto.quadrinista;

import java.time.LocalDate;

public record PessoaResponseDTO(
        Long id,
        String nome,
        String nacionalidade,
        LocalDate dataNascimento) {

}
