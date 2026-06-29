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
    private List<Colecao> colecoes = new ArrayList<>();

    public Editora(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public void addColecao(Colecao c) {
        // TODO: Descomentar a linha abaixo assim que a classe Colecao possuir o atributo e setter de Editora
//        c.setEditora(this);
        this.colecoes.add(c);
    }



    
}
