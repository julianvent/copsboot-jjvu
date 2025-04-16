package mx.jjvu.copsboot.utility.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import mx.jjvu.copsboot.utility.id.AuthServerId;

import java.util.UUID;

@Converter(autoApply = true)
public class AuthServerIdAttributeConverter implements AttributeConverter<AuthServerId, UUID> {
    @Override
    public UUID convertToDatabaseColumn(AuthServerId attribute) {
        return attribute.value();
    }

    @Override
    public AuthServerId convertToEntityAttribute(UUID dbData) {
        return new AuthServerId(dbData);
    }
}
