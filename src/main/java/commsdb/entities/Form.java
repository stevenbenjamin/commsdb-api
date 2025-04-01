package commsdb.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Form extends PanacheEntity {
    public String name;
    public String description; 
    public Long flexSchemaId;
}

