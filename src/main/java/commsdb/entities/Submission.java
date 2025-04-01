package commsdb.entities;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.sql.Timestamp;

@Entity
public class Submission extends PanacheEntity {
    public String title;
    public Boolean flagged;
    public String flagReason;
    public String data;//json
    public Long contentTypeId;
    public Long creatorId;
    public Long industryId;
    public Long partnerId;
    public Long priorityId;
    public Long schemaId;
    public Long statusId;
    public Timestamp createdTime;
    public Timestamp lastModifiedTime;
}


