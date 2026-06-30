package br.unitins.model.enums;

import lombok.Getter;

@Getter
public enum Perfil {
    ADMIN(1L, "Admin"),
    USUARIO(2L, "usuario");

    private final Long id;
    private final String nome;

    private Perfil(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static Perfil valueOf(Long id) {
        if (id == null)
            return null;

        for (Perfil perfil : values()) {
            if (perfil.getId().equals(id))
                return perfil;
        }
        return null;
    }
}
