package br.unitins.dto.quadrinista;

import java.time.LocalDate;

public record QuadrinistaResponseDTO(
        Long id,
        String nome,
        String nacionalidade,
        LocalDate dataNascimento) {

}
