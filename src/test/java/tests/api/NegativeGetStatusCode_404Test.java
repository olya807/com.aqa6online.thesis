package tests.api;

import adapters.ProjectsAdapter;
import baseEntities.BaseApiTest;
import endpoints.api.ProjectsEndpoints;
import io.restassured.response.Response;
import models.projectModels.GetResponseResult;
import models.projectModels.PostResponseResult;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class NegativeGetStatusCode_404Test extends BaseApiTest {

    @Test
    public void createProjectsTest(){
        PostResponseResult actProject = new ProjectsAdapter().postCreateProject(expProject);
        Assert.assertEquals(actProject.getResult().getCode(), expProject.getCode().toUpperCase());
    }

    @Test(dependsOnMethods = "createProjectsTest")
    public void negativeGetProjectByCodeTestWithSC_404() {
        Response response = given()
                .when()
                .get(String.format(ProjectsEndpoints.INVALID_ENDPOINT, projectCode))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .extract().response();
    }

    @Test(dependsOnMethods = "negativeGetProjectByCodeTestWithSC_404")
    public void deleteProject() {
        GetResponseResult projectDel = new ProjectsAdapter().deleteProject(projectCode.toUpperCase());
        System.out.println(projectDel);

    }
}

