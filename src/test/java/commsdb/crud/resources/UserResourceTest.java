package commsdb.crud.resources;

import commsdb.crud.entities.RuleData;
import commsdb.crud.entities.User;
import commsdb.util.JsonMatcher;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Transactional
@QuarkusTest
class UserResourceTest {

    RuleData r1= new RuleData();

    @Test
    void testGetEndpoint() {
        given()
                .when().get("/user").then()
                .statusCode(200)
                .body(is(new JsonMatcher<ArrayList<User>>((Class<ArrayList<User>>) new ArrayList<User>().getClass()) {
                }));
    }
//
//    @Transactional
//    @Test
//    void testCreateUser(){
//        User user = User.findByName("admin");
//        if (user == null){
//            User.add("admin","fname","lname",  "admin@admin.com",
//                    "password", "admin");
//
//
//             user = User.findByName("admin");
//             System.out.println(user);
//        }
//    }

}