package br.unitins.dto.endereco;

public record EnderecoCreateDTO(
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep,
//        Talvez esse cara não seja necessário
        Boolean isPrincipal
) {

}
