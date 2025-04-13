package commsdb.crud.resources;

import commsdb.crud.entities.RuleData;
import commsdb.crud.entities.User;
import commsdb.util.JsonMatcher;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

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

}