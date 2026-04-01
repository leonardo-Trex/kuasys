package br.unitins.mapper;

import br.unitins.dto.EdicaoRequestDTO;
import br.unitins.dto.EdicaoResponseDTO;
import br.unitins.model.Edicao;

public class EdicaoMapper {

    public static Edicao toEntity(EdicaoRequestDTO dto) {
        if (dto == null)
            return null;

        Edicao edicao = new Edicao();

        edicao.setNome(dto.nome());
        edicao.setDescricao(dto.descricao());
        edicao.setPreco(dto.preco());

        edicao.setNumero(dto.numero());
        edicao.setDataPublicacao(dto.dataPublicacao());

        edicao.setIsbn(dto.isbnLimpo());

        edicao.setTiragem(dto.tiragem());
        edicao.setTipoCapa(dto.tipoCapa());
        edicao.setDimensoes(dto.dimensoes());
        edicao.setGenero(dto.genero());

        return edicao;
    }

    public static EdicaoResponseDTO toResponseDTO(Edicao edicao) {
        if (edicao == null)
            return null;

        return new EdicaoResponseDTO(
                edicao.getId(),
                edicao.getNome(),
                edicao.getDescricao(),
                edicao.getPreco(),
                edicao.getNumero(),
                edicao.getDataPublicacao(),
                edicao.getIsbn(),
                edicao.getTiragem(),
                edicao.getTipoCapa().name(),
                edicao.getGenero().name(),
                edicao.getDimensoes(),
                edicao.getDataCadastro());
    }
}