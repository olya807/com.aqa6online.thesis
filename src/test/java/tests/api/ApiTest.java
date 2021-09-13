package tests.api;

import adapters.ProjectsAdapter;
import adapters.SuitesAdapter;
import baseEntities.BaseApiTest;
import core.ReadProperties;
import endpoints.api.ProjectsEndpoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.api.projectModels.GetResponseResult;
import models.api.projectModels.PostResponseResult;
import models.api.projectModels.Project;
import models.api.suitesModels.Suite;
import models.api.suitesModels.SuiteGetAllResponseResult;
import models.api.suitesModels.SuiteResponseResult;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class ApiTest extends BaseApiTest {
    private final String projectName = RandomStringUtils.randomAlphanumeric(8);
    private final String projectCode = RandomStringUtils.randomAlphabetic(5);
    private String suiteTitle = RandomStringUtils.randomAlphabetic(10);
    private int suiteId;
    private String suiteDesc = RandomStringUtils.randomAlphanumeric(10);
    private String suitePreconds = RandomStringUtils.randomAlphabetic(10);
    private String newSuiteTitle = suiteTitle + "fgfghs";

    @Test(dependsOnMethods = "deleteSuite")
    public void getAllProjectsTest() {
        GetResponseResult projectsList = new ProjectsAdapter().getAllProjects();
        System.out.println(projectsList.getResult().getEntities().get(0).getCode());
    }

    @Test
    public void createProjectsTest() {
        Project expProject = Project.builder()
                .title(projectName)
                .code(projectCode)
                .description("any description")
                .build();
        PostResponseResult actProject = new ProjectsAdapter().postCreateProject(expProject);
        Assert.assertEquals(actProject.getResult().getCode(), expProject.getCode().toUpperCase());
    }

    @Test(dependsOnMethods = "createProjectsTest")
    public void getProjectByCodeTest() {
        PostResponseResult project = new ProjectsAdapter().getProject(projectCode.toUpperCase());
        Assert.assertEquals(projectName, project.getResult().getTitle());
    }

 @Test(dependsOnMethods = "getProjectByCodeTest")
    public void negativeGetProjectByCodeTestWithSC_404() {
     Response response = given()
             .when()
             .get(String.format(ProjectsEndpoints.INVALID_ENDPOINT,projectCode))
             .then()
             .log().body()
             .statusCode(HttpStatus.SC_NOT_FOUND)
             .extract().response();
    }

    @Test(dependsOnMethods = "getProjectByCodeTest")
    public void negativeGetProjectByCodeTestWithSC_401() {
     Response response = given()
             .when()
             .header("Token",RandomStringUtils.randomNumeric(15))
             .get(String.format(ProjectsEndpoints.GET_PROJECT,projectCode))
             .then()
             .log().body()
             .statusCode(HttpStatus.SC_UNAUTHORIZED)
             .extract().response();
    }

   @Test(dependsOnMethods = "postCreateTestSuite")
    public void negativePatchUpdateProjectWithSC_405() {
       Project project = Project.builder()
               .title(projectName + "qwerty")
               .code(projectCode)
               .description("lorem ipsum")
               .build();
       Response response = given()
               .body(project, ObjectMapperType.GSON)
               .when()
               .patch(String.format(ProjectsEndpoints.UPDATE_PROJECT,projectCode))
               .then()
               .log().body()
               .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED)
               .extract().response();
    }

    @Test(dependsOnMethods = "negativePatchUpdateProjectWithSC_405")
    public void negativePatchUpdateProjectWithSC_404() {
       Project project = Project.builder()
               .title(projectName + "qwerty")
               .code(projectCode)
               .description("lorem ipsum")
               .build();
       Response response = given()
               .body(project, ObjectMapperType.GSON)
               .when()
               .patch(String.format(ProjectsEndpoints.INVALID_ENDPOINT,projectCode))
               .then()
               .log().body()
               .statusCode(HttpStatus.SC_NOT_FOUND)
               .extract().response();
    }

    @Test(dependsOnMethods = "createProjectsTest")
    public void postCreateTestSuite() {

        String suiteDesc = RandomStringUtils.randomAlphanumeric(255);
        String suitePreconds = RandomStringUtils.randomAlphanumeric(255);

        Suite expSuite = Suite.builder()
                .title(suiteTitle)
                .description(suiteDesc)
                .preconditions(suitePreconds)
                .build();
        SuiteResponseResult suite = new SuitesAdapter().postCreateSuite(expSuite, projectCode.toUpperCase());
        suiteId = suite.getResult().getId();
    }

    @Test(dependsOnMethods = "postCreateTestSuite")
    public void patchUpdateSuite() {
        Suite expSuite = Suite.builder()
                .title(newSuiteTitle)
                .description(suiteDesc + "dgfsg")
                .preconditions(suitePreconds + "sfaf")
                .build();
        PostResponseResult suite = new SuitesAdapter().updateSuite(expSuite, projectCode.toUpperCase(), suiteId);
        Assert.assertTrue(suite.isStatus());
    }

    @Test(dependsOnMethods = "patchUpdateSuite")
    public void getAllSuitesTest() {
        SuiteGetAllResponseResult suiteList = new SuitesAdapter().getAllSuites(projectCode.toUpperCase());
        Assert.assertEquals(suiteList.getResult().getEntities().get(0).getTitle(), newSuiteTitle);
    }

    @Test(dependsOnMethods = "getAllSuitesTest")
    public void deleteSuite() {
        PostResponseResult suiteDel = new SuitesAdapter().deleteSuite(projectCode.toUpperCase(), suiteId);
        Assert.assertTrue(suiteDel.isStatus());

    }

    @Test(dependsOnMethods = "getAllProjectsTest")
    public void deleteProject() {
        GetResponseResult projectDel = new ProjectsAdapter().deleteProject(projectCode.toUpperCase());
        System.out.println(projectDel);

    }
}