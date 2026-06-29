package br.unitins.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_credito")
@Getter
@NoArgsConstructor
public class Credito extends BaseEntity {

    @Setter
    @Column(nullable = false)
    private String funcao; // TODO: evoluir para um enum

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quadrinho_id", nullable = false)
    private Quadrinho quadrinho;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quadrinista_id", nullable = false)
    private Quadrinista quadrinista;
}
