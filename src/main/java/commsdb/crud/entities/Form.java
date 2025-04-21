package commsdb.crud.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.logging.Log;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static jakarta.persistence.CascadeType.PERSIST;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Form extends   PanacheEntityBase {

   @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "form_generator")
   @SequenceGenerator(name="form_generator", sequenceName = "form_id_seq")
    public Long id;


    public String name;
    public String description;

     @OneToMany(
             mappedBy = "form",
             cascade = PERSIST,
             orphanRemoval = true,
             fetch = FetchType.EAGER
     )
    public List<FormField> fields ;

    @OneToMany(
            mappedBy = "form",
            cascade = PERSIST,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    public List<RuleData> rules ;

    public Optional<FormField> getField(String name){
        if (fields == null){
            Log.warnf("Can't get field %s - form fields are not populated", name);
            return Optional.empty();
        }
        return fields.stream().filter(f -> f.name.equals(name)).findFirst();
    }
}
