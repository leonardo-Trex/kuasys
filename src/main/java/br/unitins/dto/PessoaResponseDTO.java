package br.unitins.dto;

import java.time.LocalDate;

public record PessoaResponseDTO(
                Long id,
                String nome,
                String nacionalidade,
                LocalDate dataNascimento) {

}
