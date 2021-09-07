package baseEntities;

import core.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public class BaseApiTest {

    @BeforeTest
    public void setup() {

        RestAssured.baseURI = ReadProperties.getInstance().getAPI_URL();

        RestAssured.requestSpecification = given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .header("Token", ReadProperties.getInstance().getAPI_Key())
                .auth()
                .oauth2(ReadProperties.getInstance().getAPI_Key());
    }
}
