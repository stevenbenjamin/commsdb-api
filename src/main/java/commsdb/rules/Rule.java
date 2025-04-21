package commsdb.rules;

import commsdb.crud.entities.Form;
import commsdb.crud.entities.RuleData;
import commsdb.crud.entities.Submission;

public interface Rule {

    RuleApplicationResponse apply(Submission submission, Form form);

    public RuleData toRuleData(Long formId);
}
