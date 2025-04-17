package commsdb.crud.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.logging.Log;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.PERSIST;

/*
    @JsonbTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", foreignKey = @ForeignKey(name = "job_id_fk"))
    public Job job;

        @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<JobArg> arguments = new ArrayList<>();
    // ... somewhere in Job.java
    // I used reactive-pgclient so my method return Uni<T>
    public void addArgument(final JobArg jobArg) {
        arguments.add(jobArg);
        jobArg.job = this;
    }
    public static Uni<Job> insert(final UUID userId, final JobDto newJob) {
        final Job job = new Job();
        //... map fields from dto ...
        newJob.getArguments().stream().map(arg -> {
            final JobArg jobArg = new JobArg();
            //... map fields from dto ...
            return jobArg;
        }).forEach(job::addArgument);

        final Uni<Void> jobInsert = job.persist();
        final Uni<UserAction> userActionInsert = UserAction.insertAction(type, job.id, userId, null);
        return Uni.combine().all().unis(jobInsert, userActionInsert).combinedWith(result -> job);
    }
    
    
    @Entity(name = "Post")
@Table(name = "post")
public class Post {
 
    @Id
    @GeneratedValue
    private Long id;
 
    private String title;
 
    @OneToMany(
        mappedBy = "post",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<PostComment> comments = new ArrayList<>();



 @JsonbTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", foreignKey = @ForeignKey(name = "job_id_fk"))
    public Job job;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<JobArg> arguments = new ArrayList<>();
    // ... somewhere in Job.java
    // I used reactive-pgclient so my method return Uni<T>
    public void addArgument(final JobArg jobArg) {
        arguments.add(jobArg);
        jobArg.job = this;
    }
    public sta


 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idform")
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Form extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "form_generator")
    @SequenceGenerator(name="form_generator", sequenceName = "form_id_seq")
    public Long id;


    public String name;
    public String description;

     @OneToMany(
             mappedBy = "form",
             cascade = PERSIST,
             orphanRemoval = true,
             fetch = FetchType.EAGER
     )
    public List<FormField> fields ;

    public Optional<FormField> getField(String name){
        if (fields == null){
            Log.warnf("Can't get field %s - form fields are not populated", name);
            return Optional.empty();
        }
        return fields.stream().filter(f -> f.name.equals(name)).findFirst();

    }
}
