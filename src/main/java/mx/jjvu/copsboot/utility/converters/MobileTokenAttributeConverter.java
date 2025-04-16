package mx.jjvu.copsboot.utility.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mx.jjvu.copsboot.model.user.MobileToken;

@Converter(autoApply = true)
public class MobileTokenAttributeConverter implements AttributeConverter<MobileToken, String> {
    @Override
    public String convertToDatabaseColumn(MobileToken attribute) {
        return attribute.mobileToken();
    }

    @Override
    public MobileToken convertToEntityAttribute(String dbData) {
        return new MobileToken(dbData);
    }
}
