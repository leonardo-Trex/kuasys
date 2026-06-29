package br.unitins.model;

import br.unitins.model.enums.GeneroQuadrinho;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quadrinho")
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
    private GeneroQuadrinho genero;

    @OneToMany(mappedBy = "quadrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Edicao> edicoes = new ArrayList<>();

    @OneToMany(mappedBy = "quadrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Credito> creditos = new ArrayList<>();
}