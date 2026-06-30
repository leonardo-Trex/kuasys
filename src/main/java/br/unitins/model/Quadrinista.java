package br.unitins.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_quadrinista")
@Getter
@NoArgsConstructor
public class Quadrinista extends BaseEntity {

    @Setter
    @Column(nullable = false)
    private String nome;
    @Setter
    @Column(nullable = false, length = 50)
    private String nacionalidade;

    @Column(name = "data_nascimento", nullable = false)
    @Setter
    private LocalDate dataNascimento;
}
