package commsdb.crud.entities;

import commsdb.crud.entities.util.EnumAttributeConverter;
import commsdb.enums.Action;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Table(name="action")
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ActionData extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "action_generator")
    @SequenceGenerator(name="action_generator", sequenceName = "action_id_seq")
    public Long id;

    public String name;
    public String description;
    @Convert(converter = EnumAttributeConverter.ActionConverter.class)
    public Action action;
    @JdbcTypeCode(SqlTypes.JSON)
    public String extraData;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rule_id")
    public RuleData rule;

}

/*

     Action: (To Take)
     { FORWARD: { "destination_user": 1, "reason": "...."}} ->

         e/g/
      Would write to "TODO" queue

     ActionTaken
      ActionId, ACTION, TIMESTAMP, EXTRA_DATA {user_id, priority... }
    ---------------

    Task Queue:  ToDo : Queue:
     [userId, groupId, ACTION, Timstamp, Submission, reason, priority]

     Approval:
     Submission, TS, task -> possibly a new task], Approval, approval TS]
      -submissions

 ACTION_TYPE (enum)   (Forward, Deny, Approve, (Apply other actions), SetPriority(_) )
 ACTION_STATUS  (Possible, Pending, Chosen)
 ACTION : ROUTING ACTION TO THEORETICALLY DO  name, rule_from, extra data : { userId, ... }, status: ACTION_STATUS

 DETERMINED_ACTION : ROUTING ACTIONS DECIDED ON { actionId,

 CHOSEN ACTION: ROUTING ACTION TAKEN (log)( action, submission, user...

 TASK_QUEUE: ACTIONS PER USER
   user, submission , date, priority,

 APPROVAL



 */