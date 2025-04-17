package commsdb.crud.entities;

public enum Role {
    ADMIN,
    REVIEWER,
    EDITOR;

    public static Role fromString (String s){
        return Role.valueOf(s.toUpperCase());
    }
}
