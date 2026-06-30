package br.unitins.converter;

import br.unitins.model.enums.GeneroQuadrinho;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GeneroQuadrinhoConverter implements AttributeConverter<GeneroQuadrinho, Long> {

    @Override
    public Long convertToDatabaseColumn(GeneroQuadrinho genero) {
        return genero == null ? null : genero.getId();
    }

    @Override
    public GeneroQuadrinho convertToEntityAttribute(Long id) {
        return id == null ? null : GeneroQuadrinho.valueOf(id);
    }

}
