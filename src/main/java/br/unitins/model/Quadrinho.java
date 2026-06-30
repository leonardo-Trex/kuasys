package br.unitins.model;

import br.unitins.converter.GeneroQuadrinhoConverter;
import br.unitins.model.enums.GeneroQuadrinho;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "tb_quadrinho")
@Getter
@NoArgsConstructor
public class Quadrinho extends BaseEntity {

    @Column(nullable = false)
    @Setter
    private String titulo;

    @Column(length = 1000)
    @Setter
    private String sinopse;

    @Column(name = "codigo_genero", nullable = false)
    @Setter
    @Convert(converter = GeneroQuadrinhoConverter.class)
    private GeneroQuadrinho genero;

    @OneToMany(mappedBy = "quadrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Edicao> edicoes = new ArrayList<>();

    @OneToMany(mappedBy = "quadrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Credito> creditos = new ArrayList<>();

    public List<Edicao> getEdicoes() {
        return Collections.unmodifiableList(edicoes);
    }

    public List<Credito> getCreditos() {
        return Collections.unmodifiableList(creditos);
    }

    public void addEdicao(Edicao e) {
        if (e != null) {
            this.edicoes.add(e);
            e.setQuadrinho(this);
        }
    }

    public void removeEdicao(Edicao e) {
        if (e != null) {
            this.edicoes.remove(e);
            e.setQuadrinho(null);
        }
    }

    // Dentro de Quadrinho
    public void addCredito(Credito credito) {
        if (credito != null) {
            this.creditos.add(credito);
            credito.setQuadrinho(this);
        }
    }

    public void removeCredito(Credito credito) {
        if (credito != null) {
            this.creditos.remove(credito);
            credito.setQuadrinho(null);
        }
    }
}