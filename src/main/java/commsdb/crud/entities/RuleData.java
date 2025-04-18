package commsdb.crud.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rule")
public class RuleData extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rule_generator")
    @SequenceGenerator(name="rule_generator", sequenceName = "rule_id_seq")
    public Long id;
    public String name;
    public String ruleType;
    public String expr;
    @Nullable
    public String fieldName;//null == document
    public String description;

    @ManyToMany(mappedBy="rules", fetch = FetchType.LAZY)
    public Set<Form> forms;

}

