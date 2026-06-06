package br.unitins.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.unitins.model.enums.GeneroQuadrinho;
import br.unitins.model.enums.TipoCapa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_edicao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Edicao extends Produto {

    @Column(nullable = false)
    private Integer numero;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    private Integer tiragem;

    @Column(name = "codigo_tipo_capa", nullable = false)
    private TipoCapa tipoCapa;

    @Column(length = 50)
    private String dimensoes;

    @Column(name = "codigo_genero", nullable = false)
    private GeneroQuadrinho genero;

    @JoinColumn(name = "colecao_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Colecao colecao;

    @JoinColumn(name = "editora_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Editora editora;

    @JoinColumn(name = "quadrinho_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Quadrinho quadrinho;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edicao other = (Edicao) obj;
        if (isbn == null) {
            if (other.isbn != null)
                return false;
        } else if (!isbn.equals(other.isbn))
            return false;
        return true;
    }

}
