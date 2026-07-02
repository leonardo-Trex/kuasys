package br.unitins.mapper;

import br.unitins.model.enums.GeneroQuadrinho;
import br.unitins.model.enums.Perfil;
import br.unitins.model.enums.StatusPedido;
import br.unitins.model.enums.TipoCapa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface EnumMapper {

    default StatusPedido mapStatusPedido(Long id) {
        return StatusPedido.valueOf(id);
    }

    default Long mapStatusPedido(StatusPedido status) {
        return status == null ? null : status.getId();
    }

    default Perfil mapPerfil(Long id) {
        return Perfil.valueOf(id);
    }

    default Long mapPerfil(Perfil perfil) {
        return perfil == null ? null : perfil.getId();
    }

    default GeneroQuadrinho mapGeneroQuadrinho(Long id) {
        return GeneroQuadrinho.valueOf(id);
    }

    default Long mapGeneroQuadrinho(GeneroQuadrinho genero) {
        return genero == null ? null : genero.getId();
    }

    default TipoCapa mapTipoCapa(Long id) {
        return TipoCapa.valueOf(id);
    }

    default Long mapTipoCapa(TipoCapa capa) {
        return capa == null ? null : capa.getId();
    }
}
