package commsdb.crud.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity(name="comms_role")
public class Role extends PanacheEntity {
    public String name;
}

