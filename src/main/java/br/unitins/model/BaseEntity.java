package br.unitins.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @PrePersist
    private void preencherDataCadastro() {
        setDataCadastro(LocalDateTime.now());
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BaseEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    @Column(updatable = false)
    public int hashCode() {
        return Objects.hashCode(id);
    }
}