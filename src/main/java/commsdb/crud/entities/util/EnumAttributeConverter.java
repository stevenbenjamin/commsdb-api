package commsdb.crud.entities.util;

import commsdb.enums.ActionType;
import jakarta.persistence.AttributeConverter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class EnumAttributeConverter<T extends Enum<T>> implements AttributeConverter<T, String> {

    protected Class<T> klazz ;

    @Override
    public String convertToDatabaseColumn(T t) {
        return t.name();
    }
    @Override
    public T convertToEntityAttribute(String string) {
        return Enum.valueOf(klazz,string);
    }



}