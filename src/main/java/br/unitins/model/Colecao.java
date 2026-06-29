package br.unitins.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_colecao")
@Getter
@NoArgsConstructor
public class Colecao extends BaseEntity {

    @Column(nullable = false)
    @Setter
    private String nome;

    @Column(nullable = false)
    @Setter
    private String descricao;

    @Column(name = "data_inicio_publicacao", nullable = false)
    @Setter
    private LocalDate dataInicioPublicacao;

    @Column(name = "data_fim_publicacao")
    @Setter
    private LocalDate dataFimPublicacao;

    @OneToMany(mappedBy = "colecao", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Edicao> edicoes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "editora_id")
    @Setter
    private Editora editora;
}
