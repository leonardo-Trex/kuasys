package br.unitins.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoRequestDTO(
        @NotBlank(message = "O logradouro é obrigatório.") String logradouro,
        @NotBlank(message = "O número é obrigatório.") String numero,
        String complemento,
        @NotBlank(message = "O bairro é obrigatório.") String bairro,
        @NotBlank(message = "A cidade é obrigatória.") String cidade,
        @NotBlank(message = "O estado é obrigatório.") String estado,
        @NotBlank(message = "O CEP é obrigatório.") String cep,
        @NotNull(message = "O campo isPrincipal é obrigatório.") Boolean isPrincipal
) {

}
