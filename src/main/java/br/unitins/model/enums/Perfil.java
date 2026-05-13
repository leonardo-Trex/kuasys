package br.unitins.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum Perfil {
    ADMIN(1l, "Admin"),
    ALUNO(2l, "Aluno"),
    PROFESSOR(3l, "Professor");

    private final Long ID;
    private final String NOME;

    private Perfil(Long id, String nome) {
        this.ID = id;
        this.NOME = nome;
    }

    public Long getID() {
        return ID;
    }

    public String getNOME() {
        return NOME;
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
