package br.unitins.model;

import br.unitins.model.enums.Perfil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "tb_usuario")
@Getter
@NoArgsConstructor
public class Usuario extends BaseEntity {

    @Column(name = "keycloak_id", unique = true, nullable = false, length = 50)
    private String keycloakId;

    @Setter
    @Column(nullable = false, length = 150)
    private String nome;

    @Setter
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Setter
    @Column(nullable = false, unique = true, length = 14) // Permite salvar com máscara "000.000.000-00"
    private String cpf;

    @Setter
    @Column(length = 20)
    private String telefone;

    //    TODO: Entender o fluxo de autenticação para entender o que fazer com esse cara
    @Transient
//    @Convert(converter = PerfilConverter.class)
    private Perfil perfil;

    @Setter
    @Column(nullable = false)
    private Boolean ativo = true; // Define como ativo por padrão no cadastro

    // CascadeType.ALL + orphanRemoval garante que ao remover um endereço da lista, ele seja deletado do BD
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<Endereco> enderecos = new ArrayList<>();

    @Transient
    private String senha;

    // Construtor customizado para inicializar o usuário já com o id obrigatório do Keycloak
    public Usuario(String keycloakId) {
        this.keycloakId = keycloakId;
    }

    /**
     * Retorna uma visão somente-leitura da lista para evitar modificações externas diretas
     * força o uso dos métodos helper addEndereco e removeEndereco
     */
    public List<Endereco> getEnderecos() {
        return Collections.unmodifiableList(enderecos);
    }

    public void addEndereco(Endereco endereco) {
        if (endereco != null) {
            this.enderecos.add(endereco);
            endereco.updateUsuario(this); // Sincroniza o lado "ManyToOne" do Endereço
        }
    }

    public void removeEndereco(Endereco endereco) {
        if (endereco != null) {
            this.enderecos.remove(endereco);
            endereco.updateUsuario(null); // Desfaz o vínculo no objeto endereço antes dele ser excluído pelo orphanRemoval
        }
    }
}