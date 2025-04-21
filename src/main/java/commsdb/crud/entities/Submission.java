package commsdb.crud.entities;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Submission extends PanacheEntity {
    public String title;
    public Boolean flagged;
    public String flagReason;
    public String data;//json
    public String contentType;
    public Long creatorId;
    public Long industryId;
    public Long partnerId;
    public String priority;
    public Long schemaId;
    public String status;
    public Timestamp createdTime;
    public Timestamp lastModifiedTime;
}


