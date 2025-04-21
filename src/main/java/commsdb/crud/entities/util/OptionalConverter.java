package commsdb.crud.entities.util;

import jakarta.persistence.AttributeConverter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class OptionalConverter implements AttributeConverter<Optional<String>, String> {

    @Override
    public String convertToDatabaseColumn(Optional<String> opt) {
        return opt.orElse(null);
    }
    @Override
    public Optional<String> convertToEntityAttribute(String string) {
        return Optional.ofNullable(string);
    }
}