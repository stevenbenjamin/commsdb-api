package commsdb.crud.resources;

import commsdb.crud.entities.RuleData;
import commsdb.crud.entities.User;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

//@Transactional
@QuarkusTest
class UserResourceTest {

    RuleData r1= new RuleData();

    @Test
    void testGetEndpoint() {
        var users = given()
                .when().get("/user").then()
                .statusCode(200)
                .assertThat().extract().body().as(new TypeRef<List<User>>(){});
        System.out.println( users);
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