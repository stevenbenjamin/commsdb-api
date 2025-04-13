package commsdb.rules;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import commsdb.crud.entities.Form;
import commsdb.crud.entities.FormField;
import commsdb.crud.entities.RuleData;
import commsdb.crud.entities.Submission;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static commsdb.rules.RuleApplicationResponse.ResponseType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class OpRuleTest {

    public static record TestResult(Rule rule, Submission submission, RuleApplicationResponse response){}

    private static Form form = Form.builder().fields(List.of(
                    FormField.builder().fieldType("string").name("string1").required(false).id(null).build(),
                    FormField.builder().fieldType("integer").name("integer1").required(true).id(null).build(),
                    FormField.builder().fieldType("boolean").name("boolean1").required(true).id(null).extraData("[\"A\",\"B\"]").build()))
            .build();

    public RuleData data(String ruleType,String fieldName){
        return   RuleData.builder().fieldName(fieldName).ruleType(ruleType).build();
    }

    static Submission submission(String name, Object value) throws JsonProcessingException {
      return   Submission.builder().data(
                new ObjectMapper().writeValueAsString(Map.of(name,  value))).build();
    }
    @Test public void testEqRule() throws Exception {
       var rule = OpRule.EQ("string1", "value");
        assertEquals ( rule.apply(submission("string1", "value"), form),new RuleApplicationResponse(ACCEPT, "string1"));
        assertEquals ( rule.apply(submission("string1", "BAD VALUE"),form),new RuleApplicationResponse(REJECT, "string1"));
        assertEquals (rule.apply(submission("BAD NAME", "BAD VALUE"),form),new RuleApplicationResponse(NO_DATA, "string1"));
   }
    @Test public void testIntRule() throws Exception {
        var rule = OpRule.LT("integer1", 10);
        assertEquals ( rule.apply(submission("integer1", 1), form),new RuleApplicationResponse(ACCEPT, "integer1"));
        assertEquals ( rule.apply(submission("integer1", 20), form),new RuleApplicationResponse(REJECT, "integer1"));
        assertEquals (rule.apply(submission("dont know", 1), form),new RuleApplicationResponse(NO_DATA, "integer1"));
    }


}
