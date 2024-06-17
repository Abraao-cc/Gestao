package com.timuila.gestao.convert;

/**
 *
 * @author abraao
 */
//@Converter(autoApply = true)
public class DiaSemanaConvert{/// implements AttributeConverter<DiaSemana, String> {
/**
    @Override
    public String convertToDatabaseColumn(DiaSemana attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public DiaSemana convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(DiaSemana.values())
                .filter((tel) -> tel.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
*/
}
