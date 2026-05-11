package br.unitins.model;

import java.time.LocalDateTime;

import br.unitins.model.enums.Perfil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario extends DefaultEntity {

    @Column(unique = true, nullable = false)
    private String login;

    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;

    private String nome;

    @Column(unique = true)
    private String email;
    private String senha;
    private Perfil perfil;
    private Boolean ativo;

    public Usuario() {
    }

    public Usuario(Long id, String login, String nome, String email, String senha, String senhaHash, Perfil perfil, Boolean ativo, LocalDateTime dataCadastro) {
        this.login = login;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.senhaHash = senhaHash;
        this.perfil = perfil;
        this.ativo = ativo;
     
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((super.getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }

}

