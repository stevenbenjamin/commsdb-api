package commsdb.crud.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Industry extends PanacheEntity {
    public String name;
}
