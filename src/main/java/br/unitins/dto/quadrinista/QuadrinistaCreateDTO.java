package br.unitins.dto.quadrinista;

import java.time.LocalDate;

public record QuadrinistaCreateDTO(
        String nome,
        String nacionalidade,
        LocalDate dataNascimento) {
}
