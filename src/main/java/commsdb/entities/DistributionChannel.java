package commsdb.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class DistributionChannel extends PanacheEntity {
    public String name;
}
