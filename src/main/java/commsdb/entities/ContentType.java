package commsdb.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class ContentType extends PanacheEntity {
    public String name;
}
