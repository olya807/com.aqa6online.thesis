package tests.api;

import baseEntities.BaseApiTest;
import endpoints.ProjectsEndpoints;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class apiTest extends BaseApiTest {

    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    String randomProjectName = RandomStringUtils.randomAlphanumeric(15);
    String randomProjectCode = RandomStringUtils.randomAlphabetic(10).toUpperCase(Locale.ROOT);

    @Test
    public void getAllProjectsApiTest1() {

        Response httpResponse = given()
                .request(Method.GET, ProjectsEndpoints.GET_PROJECTS);

        LOGGER.error(httpResponse.getBody().asPrettyString());
        Assert.assertEquals(
                httpResponse.getStatusCode(),
                200
        );
    }

    @Test
    public void getAllProjectsApiTest2() {

        given()
                .when()
                .get(ProjectsEndpoints.GET_PROJECTS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body()
                .body("result.entities.get(0).code", is("DEMO"));
    }

    @Test
    public void getProjectApiTest3() {

        String projectCode = "DEMO";
        String endpoint = String.format(ProjectsEndpoints.GET_PROJECT, projectCode);

        given()
                .when()
                .get(endpoint)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body()
                .body("status", is(true));

    }

    @Test
    public void getAllProjectsApiTest4() {

        given()
                .when()
                .get(ProjectsEndpoints.GET_PROJECTS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body()
                .extract().body();
    }

    @Test
    public void addProjectsApiTest5() {

        LOGGER.error(String.format(
                "\nRandom Project Name is '%s'\nRandom Project Code is '%s'",
                randomProjectName,
                randomProjectCode
        ));

        given()
                .body(String.format(
                        "{\"title\":\"%s\", \"code\": \"%s\"}",
                        randomProjectName,
                        randomProjectCode
                ))
                .when()
                .post(ProjectsEndpoints.ADD_PROJECT)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body()
                .body("status", is(true));
    }

    @Test
    public void addProjectsApiTest6() {

        LOGGER.error(String.format(
                "\nRandom Project Name is '%s'\nRandom Project Code is '%s'",
                randomProjectName,
                randomProjectCode
        ));

        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("title", randomProjectName);
        jsonBody.put("code", randomProjectCode);

        given()
                .body(jsonBody)
                .when()
                .post(ProjectsEndpoints.ADD_PROJECT)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body()
                .body("status", is(true));
    }

    @Test
    public void addProjectsApiTest7() {

        LOGGER.error(String.format(
                "\nRandom Project Name is '%s'\nRandom Project Code is '%s'",
                randomProjectName,
                randomProjectCode
        ));

        /*given()
                .body()
                .when()
                .post(ProjectsEndpoints.ADD_PROJECT)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body()
                .body("status", is(true));*/
    }
}
