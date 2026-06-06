package br.unitins.service.interfaces;

import java.util.List;

import br.unitins.model.Edicao;

public interface WishlistService {

    List<Edicao> listar(String keycloakId);

    void adicionar(String keycloakId, Long edicaoId);

    void remover(String keycloakId, Long edicaoId);
}
