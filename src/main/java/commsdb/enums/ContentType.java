package commsdb.enums;

import commsdb.crud.entities.util.EnumAttributeConverter;

public enum ContentType {
    Pdf,
    Text,
    Video;

    public static  class Converter extends EnumAttributeConverter<ContentType> {
        public Converter(){super(ContentType.class);}
    }
}
