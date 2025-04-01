package commsdb.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;

@Entity
public class Rule extends PanacheEntity {
    public String name;
    public String ruleType;
    public String rule;
    @Nullable
    public String field_name;//null == document
    public String description;

}

