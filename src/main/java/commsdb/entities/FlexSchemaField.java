package commsdb.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
public class FlexSchemaField extends PanacheEntity {
    public String name;
    public String description;
    public Boolean required;
    public String fieldType;
    public Long flexSchemaId;
    @JdbcTypeCode(SqlTypes.JSON)
    public Object extraData; //jsonb
}
