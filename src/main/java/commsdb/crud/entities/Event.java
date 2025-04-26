package commsdb.crud.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Event extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "event_generator")
    @SequenceGenerator(name = "event_generator", sequenceName = "event_id_seq")
    public Long id;

    public String description;
    @JdbcTypeCode(SqlTypes.JSON)
    public String extraData;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_id")
    public Submission submission;
    public Timestamp timestamp;

}
