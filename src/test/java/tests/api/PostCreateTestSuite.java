package tests.api;

import adapters.ProjectsAdapter;
import adapters.SuitesAdapter;
import baseEntities.BaseApiTest;
import endpoints.api.ProjectsEndpoints;
import io.restassured.response.Response;
import models.api.projectModels.GetResponseResult;
import models.api.projectModels.PostResponseResult;
import models.api.suitesModels.SuiteResponseResult;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostCreateTestSuite extends BaseApiTest {


    @Test
    public void createProjectsTest(){
        PostResponseResult actProject = new ProjectsAdapter().postCreateProject(expProject);
        Assert.assertEquals(actProject.getResult().getCode(), expProject.getCode().toUpperCase());
    }
    @Test(dependsOnMethods = "createProjectsTest")
    public void postCreateTestSuite() {
        SuiteResponseResult suite = new SuitesAdapter().postCreateSuite(expSuite, projectCode.toUpperCase());
    }

    @Test(dependsOnMethods = "postCreateTestSuite")
    public void deleteProject() {
        GetResponseResult projectDel = new ProjectsAdapter().deleteProject(projectCode.toUpperCase());
        System.out.println(projectDel);

    }
}

