package commsdb.rules;

import commsdb.crud.entities.ActionData;
import commsdb.crud.entities.Form;
import commsdb.crud.entities.FormField;
import commsdb.enums.Action;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.List;

import static commsdb.util.JsonUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@Transactional
public class RuleActionTest {

        @       jakarta.inject.Inject
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
    public void testCreate() throws Exception{

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

            // CREATE A FORM
            Form form = Form.builder().name("form1111111").description("form1 description").build();

            form.fields =  List.of(
                    FormField.builder().fieldType("string").name("string1").required(false).form(form).build(),
                    FormField.builder().fieldType("number").name("number1").required(true).form(form).build(),
                    FormField.builder().fieldType("choice").name("choice1").required(true).form(form)
                            .extraData("[\"A\",\"B\"]").build());


            form.persistAndFlush();
            var eqRule =OpRule.EQ("string1", "value");
            var eqData =  eqRule.toRuleData(form.id);
            eqData.actions = List.of(
                    ActionData.builder().action(Action.Approve).rule(eqData).name("pass").build(),
                    ActionData.builder().action(Action.Deny).rule(eqData).name("fail").build());

            var booleanRule =OpRule.EQ("bool1", true);
            var boolData = booleanRule.toRuleData(form.id);
            boolData.actions = List.of(
                    ActionData.builder().action(Action.Forward).name("forward").rule(boolData).extraData("{\"user\":1}").build(),
                    ActionData.builder().action(Action.Deny).name("fail").rule(boolData).build());

            eqData.persistAndFlush();
            boolData.persistAndFlush();
        
            entityManager.refresh(form);
            entityManager.refresh(eqData);
            entityManager.refresh(boolData);
            System.out.println(toJsonString(form));
            //verify form exists
            Form f2 = Form.findById(form.id, LockModeType.PESSIMISTIC_READ);

            System.out.println(toJsonString(f2));
//            Optional<Form> f3 = Form.findByIdOptional(8);
//
//            System.out.println(toJsonString(f3.orElse(new Form())));
            assertTrue(f2.id > 0, "form id is set");
            assertEquals(3, f2.fields.size(), "form has 3 fields set");
            assertEquals(2, f2.rules.size(), "form has 2 rules set");
            f2.rules.forEach(r ->
                    assertEquals(2, r.actions.size(), r.expr + " has 2 actions"));


            // on delete deletes rules


//        Submission submission = Submission.builder()
//                .title("title")
//                .partnerId(1l)
//                .priorityId(1l)
//                .creatorId(1l).data("")
//                .industryId(1l)
//                .schemaId(1l)
//                .build();

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
