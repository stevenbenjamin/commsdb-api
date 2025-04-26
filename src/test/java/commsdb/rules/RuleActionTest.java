package commsdb.rules;

import commsdb.crud.entities.OnRuleAction;
import commsdb.crud.entities.Form;
import commsdb.crud.entities.FormField;
import commsdb.crud.entities.Submission;
import commsdb.enums.ActionType;
import commsdb.enums.Priority;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static commsdb.util.JsonUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@Transactional
public class RuleActionTest {

    @jakarta.inject.Inject
    EntityManager entityManager;

//    @QuarkusTest
//    public class StaticContentTest {
//
//        @TestHTTPEndpoint(GreetingResource.class)
//        @TestHTTPResource
//        URL url;
//
//        @Test
//        public void testIndexHtml() throws IOException {
//            try (InputStream in = url.openStream()) {
//                String contents = new String(in.readAllBytes(), StandardCharsets.UTF_8);
//                Assertions.assertEquals("hello", contents);
//            }
//        }`
//    }
    /*

        Create a submission

       */

    @Test
    public void testCreate() throws Exception {

        /**
         * Create form, with fields
         *
         * Create some rules
         *
         * Link rules to the form
         *
         * Submit using form
         *
         * TEst: does rule apply to this form?
         *
         * On submission apply rules -> Generate recommendations
         *
         * ====
         * Rule parser : String -> Rule
         * ====
         * Endpoints
         *
         * > Submit with form
         * > Get stuff for me
         * > Assign to a person/Stage
         * > Get all live stuff
         * > Creatrs:
         * > Form creator
         * > Rule creator
         * > Test for whether rule can be applied to a form.
         * >
         *
         */

         /*
         CREATE A FORM, which includes the fields "string1", "number1", and "bool1"
          */
        Form form = Form.builder().name("test_form").description("form1 description").build();

        form.fields = List.of(
                FormField.builder().fieldType("string").name("string1").required(false).form(form).extraData("[\"A\",\"B\"]").build(),
                FormField.builder().fieldType("number").name("number1").required(true).form(form).build(),
                FormField.builder().fieldType("bool").name("bool1").required(true).form(form).build());

        /*
        The form has 2 rules attached, one that says
         - if "string1" == "value"
                - Approve
               - forward to the user (username == "editor")

         - if bool1
               - forward to the user (username == "editor")
               - FAIL
         */

        form.persistAndFlush();
        var eqRule = OpRule.EQ("string1", "value");
        var eqData = eqRule.toRuleData(form.id);
        eqData.actions = List.of(
                OnRuleAction.builder().actionType(ActionType.Approve).rule(eqData).name("pass").build(),
                OnRuleAction.builder().actionType(ActionType.Forward).rule(eqData).name("forward").extraData("{\"userName\",\"editor\" }").build());

        var booleanRule = OpRule.EQ("bool1", true);
        var boolData = booleanRule.toRuleData(form.id);
        boolData.actions = List.of(OnRuleAction.builder().actionType(ActionType.Forward).name("forward").rule(boolData).extraData("{\"userName\":\"editor\"}").build(),
                OnRuleAction.builder().actionType(ActionType.Deny).name("fail").rule(boolData).build());

        eqData.persistAndFlush();
        boolData.persistAndFlush();

        entityManager.refresh(form);
        entityManager.refresh(eqData);
        entityManager.refresh(boolData);
        //verify form exists
        Form f2 = Form.findById(form.id, LockModeType.PESSIMISTIC_READ);

        assertTrue(f2.id > 0, "form id is set");
        assertEquals(3, f2.fields.size(), "form has 3 fields set");
        assertEquals(2, f2.rules.size(), "form has 2 rules set");
        f2.rules.forEach(r -> assertEquals(2, r.actions.size(), r.expr + " has 2 actions"));

        /*
                create submission
                        - attached to this form
                        - data = { "string1" : "value", "bool1": true }

         */

        Submission submission = Submission.builder()
                .title("title")
                .data (toJson(Map.of("string1", "value", "bool1", true)))
                .priority(Priority.Medium)
                .creatorId(1l)
                .industryId(1l)
                .schemaId(1l)
                .build();




            /* run actions on submission -> creates recommendations



             */

            /* Select recommendations
            *
            *  recommendations -> actions. Some of these put submission into task queue.
            */


            /*
                editors task queue has submission with "to review"

             */

            /* Actions has
                - forwarded to editor
             
             */
            
            
            /*
            Events (log) has
                - submitted
                - routed to editor
             */

        // on delete deletes rules



    }

    //Form form = new Form();

    //var rules = new ArrayList<RuleAction>();
    /*
        Apply a form:
         -> Apply all rules
         -> return a set of actions/reccomendations
     */

//    RuleAction r = new RuleAction() {
//        @Override
//        public boolean apply(Submission submission, Form schema) {
//            return true;
//        }
//    };


}
