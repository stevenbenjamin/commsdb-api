package commsdb.crud.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rule")
public class RuleData extends PanacheEntity {
    public String name;
    public String ruleType;
    public String rule;
    @Nullable
    public String fieldName;//null == document
    public String description;

}

