package commsdb.crud.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Optional;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "form")
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FormField extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,  generator = "form_field_generator")
    @SequenceGenerator(name = "form_field_generator", sequenceName = "form_field_id_seq", schema="public",allocationSize=1)
    public Long id;
    public String name;
    public String description;
    public Boolean required;
    public String fieldType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="form_id")
    public Form form;

    @JdbcTypeCode(SqlTypes.JSON)
    public String extraData; //jsonb
}
