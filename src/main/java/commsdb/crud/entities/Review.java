package commsdb.crud.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Review extends PanacheEntity {
    public Long sequenceNum;
    public Long reviewerId; 
    public Long submissionId;
    public String content;
}

