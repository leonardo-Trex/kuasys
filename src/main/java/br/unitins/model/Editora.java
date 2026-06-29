package br.unitins.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_editora")
@Getter
@NoArgsConstructor
public class Editora extends BaseEntity {

    @Setter
    private String nome;
    @Setter
    private String cnpj;

    @OneToMany(mappedBy = "editora", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private final List<Colecao> colecoes = new ArrayList<>(); // Essa lista não pode ser alterada! só extendida!

    public void addColecao(Colecao c) {
        c.setEditora(this);
        this.colecoes.add(c);
    }

    public void removeColecao(Colecao c) {
        this.colecoes.remove(c);
    }

}
