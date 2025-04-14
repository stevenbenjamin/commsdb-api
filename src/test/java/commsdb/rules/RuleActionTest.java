package commsdb.rules;

import commsdb.crud.entities.Form;
import commsdb.crud.entities.FormField;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
public class RuleActionTest {


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

    @Transactional
    @Test
    public void testCreate() {

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
        try {

            Form form = Form.builder().name("form1111111").description("form1 description").fields(List.of(
                    FormField.builder().fieldType("string").name("string1").required(false).id(null).build(),
                    FormField.builder().fieldType("number").name("number1").required(true).id(null).build(),
                    FormField.builder().fieldType("choice").name("choice1").required(true).id(null).extraData("[\"A\",\"B\"]").build()))
                    .build();

            form.persistAndFlush();
            Form f2 = Form.findById(form.id);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

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
