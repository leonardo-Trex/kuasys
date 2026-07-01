package br.unitins.service;

import br.unitins.dto.usuario.UsuarioCreateDTO;
import br.unitins.dto.usuario.UsuarioResponseDTO;
import br.unitins.mapper.UsuarioMapper;
import br.unitins.model.Usuario;
import br.unitins.repository.UsuarioRepository;
import br.unitins.service.interfaces.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {
    //  TODO: Mappers funcionais são muito necessários aqui!!! o service não funciona sem eles!
    //  TODO: o usuario nasce ativo!
    @Inject
    UsuarioRepository repository;

    @Inject
    UsuarioMapper mapper;

    @Override
    public List<UsuarioResponseDTO> findAll() {

        List<Usuario> lista = repository.findAll().list();
        return mapper.toResponseDTO(lista);
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {

        Usuario u = repository.findById(id);
        return mapper.toResponseDTO(u);
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String nome) {

        List<Usuario> usuarios = repository.findByNome(nome).list();
        return mapper.toResponseDTO(usuarios);
    }

    @Override
    public UsuarioResponseDTO findByLogin(String login) {

        Usuario u = repository.findByLogin(login);
        return mapper.toResponseDTO(u);
    }

    @Override
    public UsuarioResponseDTO findByEmail(String email) {

        Usuario u = repository.findByEmail(email);
        return mapper.toResponseDTO(u);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO create(UsuarioCreateDTO dto) {
        Usuario u = mapper.toEntity(dto);
        repository.persist(u);
        return mapper.toResponseDTO(u);
    }

// Esse cara aqui vai se beneficiar do DTO exclusivo então deixo para depois
//    @Override
//    @Transactional
//    //    TODO: por enquanto sem keycloak, esse cara precisa não lidar com sincronia entre o backend e o keycloak
//    public UsuarioResponseDTO update(Long id, UsuarioCreateDTO usuario) {

    /// /       TODO: Verificar se existe no banco aí atualiza
    /// /        TODO: Melhoria para um exception?
//        Usuario u = repository.findById(id);
//        if(u == null)
//            return null;
//        repository.update(u);
//        return null;
//    }

//    Impedir o erro da IDE
    public UsuarioResponseDTO update(Long id, UsuarioCreateDTO usuario) {
        return null;
    }


    //    TODO: por enquanto sem keycloak, esse cara precisa não lidar com sincronia entre o backend e o keycloak
    @Override
    @Transactional
    public void delete(Long id) {
//        TODO: Verificar a necessidade de um try-catch aqui
        repository.deleteById(id);
    }

// Aqui fica comentado até a integração com keycloak
//    @Override
//    public UsuarioResponseDTO findByKeycloakId(String keycloakId) {
//        Usuario u = repository.findByKeycloakId(keycloakId);
//        return UsuarioMapper.toResponseDTO(u);
//    }
}
