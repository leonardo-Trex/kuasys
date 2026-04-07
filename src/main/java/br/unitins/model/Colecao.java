package br.unitins.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_colecao")
public class Colecao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "data_inicio_publicacao", nullable = false)
    private LocalDate dataInicioPublicacao;

    @Column(name = "data_fim_publicacao", nullable = false)
    private LocalDate dataFimPublicacao;

    @OneToMany(mappedBy = "colecao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Edicao> edicoes = new ArrayList<>();

    public Colecao() {
    }

    public Colecao(Long id, String nome, String descricao, LocalDate dataInicioPublicacao,
            LocalDate dataFimPublicacao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicioPublicacao = dataInicioPublicacao;
        this.dataFimPublicacao = dataFimPublicacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicioPublicacao() {
        return dataInicioPublicacao;
    }

    public void setDataInicioPublicacao(LocalDate dataInicioPublicacao) {
        this.dataInicioPublicacao = dataInicioPublicacao;
    }

    public LocalDate getDataFimPublicacao() {
        return dataFimPublicacao;
    }

    public void setDataFimPublicacao(LocalDate dataFimPublicacao) {
        this.dataFimPublicacao = dataFimPublicacao;
    }

    public List<Edicao> getEdicoes() {
        return edicoes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Colecao other = (Colecao) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
