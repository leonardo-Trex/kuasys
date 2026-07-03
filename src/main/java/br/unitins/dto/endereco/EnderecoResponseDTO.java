package br.unitins.dto.endereco;

public record EnderecoResponseDTO(
        Long id,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep,
//        Boolean isPrincipal, TODO fica desativado até eu resolver a lógica de principal
        Long usuarioId
) {

}
