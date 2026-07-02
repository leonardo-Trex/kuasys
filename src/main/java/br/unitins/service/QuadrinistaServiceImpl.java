package br.unitins.service;

import br.unitins.dto.quadrinista.QuadrinistaCreateDTO;
import br.unitins.dto.quadrinista.QuadrinistaResponseDTO;
import br.unitins.mapper.QuadrinistaMapper;
import br.unitins.model.Quadrinista;
import br.unitins.repository.QuadrinistaRepository;
import br.unitins.service.interfaces.QuadrinistaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class QuadrinistaServiceImpl implements QuadrinistaService {

    @Inject
    QuadrinistaRepository repository;

    @Inject
    QuadrinistaMapper mapper;

    @Override
    public List<QuadrinistaResponseDTO> findAll() {

        List<Quadrinista> lista = repository.findAll().list();
        return mapper.toResponseDTO(lista);
    }

    @Override
    public QuadrinistaResponseDTO findById(Long id) {

        Quadrinista q = repository.findById(id);
        return mapper.toResponseDTO(q);
    }

    @Override
    public List<QuadrinistaResponseDTO> findByNome(String nome) {

        List<Quadrinista> lista = repository.findByNome(nome).list();
        return mapper.toResponseDTO(lista);
    }

    @Override
    @Transactional
    public QuadrinistaResponseDTO create(QuadrinistaCreateDTO dto) {

        Quadrinista q = mapper.toEntity(dto);
        repository.persist(q);
        return mapper.toResponseDTO(q);
    }

    //    TODO: Esse cara existe para evitar o erro da IDE
    @Override
    public void update(Long id, QuadrinistaCreateDTO dto) {

    }

//  TODO:  Consertar esse cara quando existir o UpdateDTO.
//    @Override
//    @Transactional
//    public void update(Long id, Quadrinista quadrinista) {
//
//        Quadrinista q = repository.findById(id);
//        p.setNome(quadrinista.getNome());
//        p.setNacionalidade(quadrinista.getNacionalidade());
//        p.setDataNascimento(quadrinista.getDataNascimento());
//
//    }


    @Override
    @Transactional
    public void delete(Long id) {

        repository.deleteById(id);
    }

}
