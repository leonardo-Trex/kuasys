package br.unitins.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.unitins.model.enums.GeneroQuadrinho;
import br.unitins.model.enums.TipoCapa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_edicao")
public class Edicao extends Produto {

    @Column(nullable = false)
    private Integer numero;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    private Integer tiragem;

    @Column(name = "codigo_tipo_capa", nullable = false)
    private TipoCapa tipoCapa;

    @Column(length = 50)
    private String dimensoes;

    @Column(name = "codigo_genero", nullable = false)
    private GeneroQuadrinho genero;

    @JoinColumn(name = "colecao_id")
    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    private Colecao colecao;

    public Edicao() {
        super();
    }

    public Edicao(Long id, String nome, String descricao, BigDecimal preco, LocalDateTime dataCadastro, Integer numero,
            LocalDate dataPublicacao, String isbn, Integer tiragem, TipoCapa tipoCapa, String dimensoes,
            GeneroQuadrinho genero, Colecao colecao) {
        super(id, nome, descricao, preco, dataCadastro);
        this.numero = numero;
        this.dataPublicacao = dataPublicacao;
        this.isbn = isbn;
        this.tiragem = tiragem;
        this.tipoCapa = tipoCapa;
        this.dimensoes = dimensoes;
        this.genero = genero;
        this.colecao = colecao;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getTiragem() {
        return tiragem;
    }

    public void setTiragem(Integer tiragem) {
        this.tiragem = tiragem;
    }

    public TipoCapa getTipoCapa() {
        return tipoCapa;
    }

    public void setTipoCapa(TipoCapa tipoCapa) {
        this.tipoCapa = tipoCapa;
    }

    public String getDimensoes() {
        return dimensoes;
    }

    public void setDimensoes(String dimensoes) {
        this.dimensoes = dimensoes;
    }

    public GeneroQuadrinho getGenero() {
        return genero;
    }

    public void setGenero(GeneroQuadrinho genero) {
        this.genero = genero;
    }

    public Colecao getColecao() {
        return colecao;
    }

    public void setColecao(Colecao colecao) {
        this.colecao = colecao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edicao other = (Edicao) obj;
        if (isbn == null) {
            if (other.isbn != null)
                return false;
        } else if (!isbn.equals(other.isbn))
            return false;
        return true;
    }

}
