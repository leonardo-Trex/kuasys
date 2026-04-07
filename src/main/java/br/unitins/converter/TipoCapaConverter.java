package br.unitins.converter;

import br.unitins.model.enums.TipoCapa;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoCapaConverter implements AttributeConverter<TipoCapa, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoCapa tipoCapa) {
        return tipoCapa == null ? null : tipoCapa.getID();
    }

    @Override
    public TipoCapa convertToEntityAttribute(Long id) {
        return TipoCapa.valueOf(id);
    }

}
