package commsdb.crud.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rule")
public class RuleData extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rule_generator")
    @SequenceGenerator(name = "rule_generator", sequenceName = "rule_id_seq")
    public Long id;
    public String name;
    public String ruleType;
    public String expr;
    @Nullable
    public String fieldName;
    public String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    public Form form;

    @OneToMany(mappedBy = "rule", cascade = PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    public List<OnRuleAction> actions;

}

