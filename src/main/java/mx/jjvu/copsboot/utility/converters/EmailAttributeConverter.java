package mx.jjvu.copsboot.utility.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mx.jjvu.copsboot.model.user.Email;

@Converter(autoApply = true)
public class EmailAttributeConverter implements AttributeConverter<Email, String> {
    @Override
    public String convertToDatabaseColumn(Email attribute) {
        return attribute.email();
    }

    @Override
    public Email convertToEntityAttribute(String dbData) {
        return new Email(dbData);
    }
}
