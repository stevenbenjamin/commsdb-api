package commsdb.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.sql.Timestamp;

@Entity
public class Attachement extends PanacheEntity {
    public String name;
    public String description;
    public String data;//json
    public Long contentTypeId;
    public Long creatorId;
    public Long submissionId;
    public Timestamp createdTime;
    public Timestamp lastModifiedTime;
}

