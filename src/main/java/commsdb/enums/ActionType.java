package commsdb.enums;

import commsdb.crud.entities.util.EnumAttributeConverter;

public enum ActionType {
    Forward,
    Deny,
    Approve,
    None,
    SetPriority,
    ApplyRules;

    public static  class Converter extends EnumAttributeConverter<ActionType> {
        public Converter(){super(ActionType.class);}
    }
}
