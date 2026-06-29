package br.unitins.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_pessoa")
@Getter
@NoArgsConstructor
public class Pessoa extends BaseEntity {

    @Setter
    private String nome;
    @Setter
    private String nacionalidade;

    @Column(name = "data_nascimento")
    @Setter
    private LocalDate dataNascimento;
}
