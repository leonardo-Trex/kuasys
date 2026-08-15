package br.unitins.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_endereco")
@Getter
@NoArgsConstructor()
// TODO resolver a questão do endereço principal!
public class Endereco extends BaseEntity {

    @Setter
    @Column(nullable = false, length = 150)
    private String logradouro;

    @Setter
    @Column(nullable = false, length = 20) // Aceita "S/N", "Lote 10", etc.
    private String numero;

    @Setter
    @Column(length = 100)
    private String complemento;

    @Setter
    @Column(nullable = false, length = 80)
    private String bairro;

    @Setter
    @Column(nullable = false, length = 80)
    private String cidade;

    @Setter
    @Column(nullable = false, length = 2) // Garante o padrão de UF (ex: "TO", "SP")
    private String estado;

    @Setter
    @Column(nullable = false, length = 9) // Aceita "77000-000" ou "77000000"
    private String cep;

    @Setter
    @Column(name = "is_principal", nullable = false)
    private Boolean principal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}