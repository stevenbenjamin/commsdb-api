package commsdb.enums;

import commsdb.crud.entities.util.EnumAttributeConverter;

public enum Status {
    Submitted,
    Waiting,
    Approved;

    public static  class Converter extends EnumAttributeConverter<Status> {
        public Converter(){super(Status.class);}
    }
}
