package br.unitins.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @CreationTimestamp
    @Column(name = "data_cadastro", updatable = false, nullable = false)
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;


    // Usa Hibernate.getClass para funcionar corretamente com proxies.
    // Essa implementação não precisa/pode ser substituida!
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        if (Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;

        BaseEntity that = (BaseEntity) o;

        return id != null && id.equals(that.id);
    }

    // Essa implementação não precisa/pode ser substituida!
    @Override
    public final int hashCode() {
        return Hibernate.getClass(this).hashCode();
    }
}