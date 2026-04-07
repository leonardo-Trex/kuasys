package br.unitins.mapper;

import br.unitins.dto.ColecaoRequestDTO;
import br.unitins.dto.ColecaoResponseDTO;
import br.unitins.model.Colecao;
import jakarta.enterprise.context.ApplicationScoped;

public class ColecaoMapper {

    public Colecao toEntity(ColecaoRequestDTO dto) {
        if (dto == null)
            return null;

        Colecao colecao = new Colecao();
        colecao.setNome(dto.nome());
        colecao.setDescricao(dto.descricao());
        colecao.setDataInicioPublicacao(dto.dataInicioPublicacao());
        colecao.setDataFimPublicacao(dto.dataFimPublicacao());

        return colecao;
    }

    public ColecaoResponseDTO toResponseDTO(Colecao colecao) {
        if (colecao == null)
            return null;

        return new ColecaoResponseDTO(
                colecao.getId(),
                colecao.getNome(),
                colecao.getDescricao(),
                colecao.getDataInicioPublicacao(),
                colecao.getDataFimPublicacao());
    }
}