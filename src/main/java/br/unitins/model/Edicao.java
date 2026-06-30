package br.unitins.model;

import br.unitins.converter.TipoCapaConverter;
import br.unitins.model.enums.TipoCapa;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("EDICAO")
public class Edicao extends Produto {

    @Column(nullable = false)
    @Setter
    private Integer numero;

    @Column(name = "data_publicacao")
    @Setter
    private LocalDate dataPublicacao;

    @Column(nullable = false, unique = true, length = 20)
    @Setter
    private String isbn;

    @Setter
    private Integer tiragem;

    @Column(name = "codigo_tipo_capa", nullable = false)
    @Setter
    @Convert(converter = TipoCapaConverter.class)
    private TipoCapa tipoCapa;

    @Column(length = 50)
    @Setter
    private String dimensoes;


    //    TODO: testar a efetividade dessa tag
//    @JsonIgnore
    @JoinColumn(name = "colecao_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    private Colecao colecao;

    @JoinColumn(name = "editora_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    private Editora editora;

    @JoinColumn(name = "quadrinho_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    private Quadrinho quadrinho;
}
