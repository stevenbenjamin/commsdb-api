package commsdb.rules;

import com.fasterxml.jackson.core.JsonProcessingException;
import commsdb.crud.entities.Form;
import commsdb.crud.entities.FormField;
import commsdb.crud.entities.RuleData;
import commsdb.crud.entities.Submission;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static commsdb.rules.RuleApplicationResult.ResponseType.*;
import static commsdb.util.JsonUtil.toJson;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class OpRuleTest {

    private static final Form form = Form.builder().fields(List.of(
                    FormField.builder().fieldType("string").name("string1").required(false).id(null).build(),
                    FormField.builder().fieldType("integer").name("integer1").required(true).id(null).build(),
                    FormField.builder().fieldType("date").name("date1").required(true).id(null).build(),
                    FormField.builder().fieldType("boolean").name("boolean1").required(true).id(null).extraData("[\"A\",\"B\"]").build()))
            .build();

    public RuleData data(String ruleType,String fieldName){
        return   RuleData.builder().fieldName(fieldName).ruleType(ruleType).build();
    }

    static Submission submission(String name, Object value) throws JsonProcessingException {
      return   Submission.builder().data(toJson(Map.of(name,  value))).build();
    }
    @Test public void testEqRule() throws Exception {
       var rule = OpRule.EQ("string1", "value");
        assertEquals ( new RuleApplicationResult(ACCEPT, "string1"),rule.apply(submission("string1", "value"), form));
        assertEquals ( new RuleApplicationResult(REJECT, "string1"),rule.apply(submission("string1", "BAD VALUE"),form));
        assertEquals (new RuleApplicationResult(NO_DATA, "string1"),rule.apply(submission("BAD NAME", "BAD VALUE"),form));
   }
    @Test public void testIntRule() throws Exception {
        var rule = OpRule.LT("integer1", 10);
        assertEquals ( new RuleApplicationResult(ACCEPT, "integer1"),rule.apply(submission("integer1", 1), form));
        assertEquals (new RuleApplicationResult(REJECT, "integer1"), rule.apply(submission("integer1", 20), form));
        assertEquals (new RuleApplicationResult(NO_DATA, "integer1"),rule.apply(submission("dont know", 1), form));
    }
    @Test public void testBooleanRule() throws Exception {
        var rule = OpRule.EQ("boolean1", true);
        assertEquals (new RuleApplicationResult(ACCEPT, "boolean1"), rule.apply(submission("boolean1", true), form));
        assertEquals (new RuleApplicationResult(REJECT, "boolean1"), rule.apply(submission("boolean1", false), form));
        assertEquals (new RuleApplicationResult(NO_DATA, "boolean1"),rule.apply(submission("dont know", true), form));
    }

    @Test public void testDateRule() throws Exception {
        var d1=  "2011-12-03" ;
        var d2=  "2011-12-04";
        var d3=  "2011-12-05";

        var rule = OpRule.GT("date1", d2);

        assertEquals ( new RuleApplicationResult(ACCEPT, "date1"),rule.apply(submission("date1", d3), form));
        assertEquals (new RuleApplicationResult(REJECT, "date1"), rule.apply(submission("date1", d1), form));
        assertEquals (new RuleApplicationResult(NO_DATA, "date1"),rule.apply(submission("dont know", 1), form));
    }



}
