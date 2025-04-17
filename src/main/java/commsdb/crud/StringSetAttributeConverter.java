package commsdb.crud;

import jakarta.persistence.AttributeConverter;

import java.util.HashSet;
import java.util.Set;

public class StringSetAttributeConverter implements AttributeConverter<Set<String>, String> {
    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(Set<String> strings) {
        return strings != null ? String.join(SPLIT_CHAR, strings) : "";
    }
    @Override
    public Set<String> convertToEntityAttribute(String string) {
        return string != null ? Set.of(string.split(SPLIT_CHAR)): new HashSet<String>();
    }
}