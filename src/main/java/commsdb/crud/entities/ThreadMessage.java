package commsdb.crud.entities;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class ThreadMessage extends PanacheEntity {
    public Long parentId;
    public Long threadId;
    public String content;
}
