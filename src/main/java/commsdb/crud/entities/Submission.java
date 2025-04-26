package commsdb.crud.entities;
import com.fasterxml.jackson.databind.JsonNode;
import commsdb.crud.entities.util.JsonNodeAttributeConverter;
import commsdb.enums.ActionType;
import commsdb.enums.ContentType;
import commsdb.enums.Priority;
import commsdb.enums.Status;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Map;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Submission extends PanacheEntity {
    public String title;
    public Boolean flagged;
    public String flagReason;
    @Convert(converter= JsonNodeAttributeConverter.class)
    public JsonNode data;//json
    @Convert(converter = Status.Converter.class)
    public ContentType contentType;
    public Long creatorId;
    public Long industryId;
    public Long partnerId;
    @Convert(converter = ActionType.Converter.class)
    public ActionType actionType;
    @Convert(converter = Priority.Converter.class)
    public Priority priority;
    public Long schemaId;
    @Convert(converter = Status.Converter.class)
    public Status status;
    public Timestamp createdTime;
    public Timestamp lastModifiedTime;
}


