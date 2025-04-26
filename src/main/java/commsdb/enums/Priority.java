package commsdb.enums;

import commsdb.crud.entities.util.EnumAttributeConverter;

public enum Priority {
    Low,
    Medium,
    High,
    Urgent;

    public static  class Converter extends EnumAttributeConverter<Priority> {
        public Converter(){super(Priority.class);}
    }
}
