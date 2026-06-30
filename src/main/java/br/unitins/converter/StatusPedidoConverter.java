package br.unitins.converter;

import br.unitins.model.enums.StatusPedido;
import jakarta.persistence.AttributeConverter;

public class StatusPedidoConverter implements AttributeConverter<StatusPedido, Long> {

    @Override
    public Long convertToDatabaseColumn(StatusPedido status) {
        return status == null ? null : status.getId();
    }

    @Override
    public StatusPedido convertToEntityAttribute(Long id) {
        return id == null ? null : StatusPedido.valueOf(id);
    }

}