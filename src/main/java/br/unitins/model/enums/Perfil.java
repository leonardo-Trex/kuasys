package br.unitins.model.enums;

import lombok.Getter;

@Getter
public enum Perfil {
    ADMIN(1L, "Admin"),
    USUARIO(2L, "usuario");

    private final Long ID;
    private final String NOME;

    private Perfil(Long id, String nome) {
        this.ID = id;
        this.NOME = nome;
    }

    public static Perfil valueOf(Long id) {
        if (id == null)
            return null;

        for (Perfil perfil : values()) {
            if (perfil.getID().equals(id))
                return perfil;
        }
        return null;
    }
}
