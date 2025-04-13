package commsdb.crud.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class RiskLevel extends PanacheEntity {
    public String name;
}
