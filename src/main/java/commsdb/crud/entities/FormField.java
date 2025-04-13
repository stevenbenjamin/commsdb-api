package commsdb.crud.entities;

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

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FormField extends PanacheEntityBase {

   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "form_field_generator")
    @SequenceGenerator(name = "form_field_generator", sequenceName = "form_field_id_seq", schema="public",allocationSize=1)
    public Long id;
    public String name;
    public String description;
    public Boolean required;
    public String fieldType;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="form_id", foreignKey = @ForeignKey(name="form_field_form_id_fk"))
    //public Form form;
    @ManyToOne
    @JoinColumn(name="FORM_ID")
    public Form form;

    @JdbcTypeCode(SqlTypes.JSON)
    public String extraData; //jsonb
}
