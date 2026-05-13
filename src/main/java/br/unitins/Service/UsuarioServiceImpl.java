package br.unitins.Service;

import java.util.List;

import br.unitins.Service.interfaces.UsuarioService;
import br.unitins.model.Usuario;
import br.unitins.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Override
    public List<Usuario> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Usuario findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Usuario> findByNome(String nome) {
        return repository.findByNome(nome).list();
    }

    @Override
    public Usuario findByLogin(String login) {
        return repository.findByLogin(login);
    }

    @Override
    public Usuario findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional
    public Usuario create(Usuario usuario) {
        repository.persist(usuario);
        return usuario;
    }

    @Override
    @Transactional
    public void update(Long id, Usuario usuario) {
        Usuario existing = findById(id);
        if (existing == null) {
            return;
        }
        existing.setLogin(usuario.getLogin());
        existing.setNome(usuario.getNome());
        existing.setEmail(usuario.getEmail());
        existing.setSenhaHash(usuario.getSenhaHash());
        existing.setPerfil(usuario.getPerfil());
        existing.setAtivo(usuario.getAtivo());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
