package commsdb.crud.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity(name="comms_status ")
public class Status extends PanacheEntity {
    public String name;
}
