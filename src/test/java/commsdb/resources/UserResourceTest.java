package commsdb.resources;

import commsdb.entities.User;
import commsdb.util.JsonMatcher;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
class UserResourceTest {

    @Test
    void testGetEndpoint() {
        given()
                .when().get("/user").then()
                .statusCode(200)
                .body(is(new JsonMatcher<ArrayList<User>>((Class<ArrayList<User>>) new ArrayList<User>().getClass()) {
                }));
    }

}