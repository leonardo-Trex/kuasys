package br.unitins.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_produto", discriminatorType = DiscriminatorType.STRING)
@Table(name = "tb_produto")
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor
public abstract class Produto extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @Column(length = 1000, nullable = false)
    private String descricao;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private Integer estoque;

    @Column(name = "nome_imagem")
    private String nomeImagem;
}
