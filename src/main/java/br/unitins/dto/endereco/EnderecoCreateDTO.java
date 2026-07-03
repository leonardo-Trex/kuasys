package br.unitins.dto.endereco;

/*
 * isPrincipal vai ficar desativado.
 *
 * O sistema vai precisar da lógica de ativação/desativação de todos os endereços
 * Só pode haver um ativo, ativou um desativa o anterior.
 *
 *
 * */
public record EnderecoCreateDTO(
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
//        Talvez esse cara não seja necessário
//        Boolean isPrincipal
) {

}
