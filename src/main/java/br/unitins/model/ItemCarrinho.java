//package br.unitins.model;
//
//import java.math.BigDecimal;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "item_carrinho")
//public class ItemCarrinho extends BaseEntity {
//    @Column(name = "quantidade", nullable = false)
//    private int quantidade;
//
//    @Column(name = "preco_unitario", nullable = false)
//    private BigDecimal precoUnitario;
//
//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "edicao_id", nullable = false)
//    private Edicao edicao;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "carrinho_id", nullable = false)
//    private Carrinho carrinho;
//
//    public ItemCarrinho() {
//    }
//
//    public ItemCarrinho(Edicao edicao, int quantidade, Carrinho carrinho) {
//        this.edicao = edicao;
//        this.quantidade = quantidade;
//        this.precoUnitario = edicao.getPreco();
//        this.carrinho = carrinho;
//    }
//
//    public ItemCarrinho(Edicao edicao, int quantidade, BigDecimal precoUnitario, Carrinho carrinho) {
//        this.edicao = edicao;
//        this.quantidade = quantidade;
//        this.precoUnitario = precoUnitario;
//        this.carrinho = carrinho;
//    }
//
//    public BigDecimal getPrecoUnitario() {
//        return precoUnitario;
//    }
//
//    public void setPrecoUnitario(BigDecimal precoUnitario) {
//        this.precoUnitario = precoUnitario;
//    }
//
//    public Edicao getEdicao() {
//        return edicao;
//    }
//
//    public void setEdicao(Edicao edicao) {
//        this.edicao = edicao;
//    }
//
//    public int getQuantidade() {
//        return quantidade;
//    }
//
//    public void setQuantidade(int quantidade) {
//        this.quantidade = quantidade;
//    }
//
//    public Carrinho getCarrinho() {
//        return carrinho;
//    }
//
//    public void setCarrinho(Carrinho carrinho) {
//        this.carrinho = carrinho;
//    }
//
//}
