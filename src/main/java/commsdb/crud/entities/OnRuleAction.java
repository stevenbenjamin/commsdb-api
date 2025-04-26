package commsdb.crud.entities;

import com.fasterxml.jackson.databind.JsonNode;
import commsdb.crud.entities.util.EnumAttributeConverter;
import commsdb.crud.entities.util.JsonNodeAttributeConverter;
import commsdb.enums.ActionType;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OnRuleAction extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "on_rule_action_generator")
    @SequenceGenerator(name="on_rule_action_generator", sequenceName = "on_rule_action_id_seq")
    public Long id;

    public String name;
    public String description;
    @Convert(converter = ActionType.Converter.class)
    public ActionType actionType;

    @Convert(converter= JsonNodeAttributeConverter.class)
    public JsonNode extraData;//json
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rule_id")
    public RuleData rule;

}


/*

     -- EVENT: id, SUBMIT, submission_id

CREATE TABLE IF NOT EXISTS event {
    id bigserial PRIMARY KEY,
    description varchar,
    submission_id bigserial REFERENCES submission(id),
    extra_data jsonb,
    created_time timestamp with time zone NOT NULL DEFAULT NOW()
);

 --    Action_Taken: (suggested, generated) { FORWARD: { "destination_user": 1, "reason": "...."}}
CREATE TABLE IF NOT EXISTS action_taken {
    id bigserial PRIMARY KEY,
    description varchar,
    submission_id bigserial REFERENCES submission(id),
    extra_data jsonb,
    created_time timestamp with time zone NOT NULL DEFAULT NOW()
);

-- tasks

CREATE TABLE IF NOT EXISTS task_queue {
    id bigserial PRIMARY KEY,
    user_id bigserial REFERENCES comms_user(id),
    description varchar,
    reason varchar,
    submission_id bigserial REFERENCES submission(id),
    extra_data jsonb,
    created_time timestamp with time zone NOT NULL DEFAULT NOW()
);



         e/g/
      Would write to "TODO" queue

     ActionTaken
      ActionId, ACTION, TIMESTAMP, EXTRA_DATA {user_id, priority... }, submit_id
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

 CHOSEN ACTION: ROUTING ACTION TAKEN (log)( actionType, submission, user...

 TASK_QUEUE: ACTIONS PER USER
   user, submission , date, priority,

 APPROVAL



 */