package br.unitins.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import br.unitins.model.enums.GeneroQuadrinho;

@Entity
@Table(name = "quadrinho")
public class Quadrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 1000)
    private String sinopse;

    @Column(name = "codigo_genero", nullable = false)
    private GeneroQuadrinho genero;

    @OneToMany(mappedBy = "quadrinho")
    private List<Edicao> edicoes;

    @OneToMany(mappedBy = "quadrinho")
    private List<Credito> creditos;

    public Quadrinho() {
    }

    public Quadrinho(Long id, String titulo, String sinopse, GeneroQuadrinho genero) {
        this.id = id;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.genero = genero;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public GeneroQuadrinho getGenero() {
        return genero;
    }

    public void setGenero(GeneroQuadrinho genero) {
        this.genero = genero;
    }

    public List<Edicao> getEdicoes() {
        return edicoes;
    }

    public void setEdicoes(List<Edicao> edicoes) {
        this.edicoes = edicoes;
    }

    public List<Credito> getCreditos() {
        return creditos;
    }

    public void setCreditos(List<Credito> creditos) {
        this.creditos = creditos;
    }
}