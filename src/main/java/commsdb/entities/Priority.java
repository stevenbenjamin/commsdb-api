package commsdb.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Priority extends PanacheEntity {
    public Long id;
    public String name;
}
