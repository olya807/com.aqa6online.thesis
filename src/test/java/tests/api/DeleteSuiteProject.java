package tests.api;

import adapters.ProjectsAdapter;
import adapters.SuitesAdapter;
import baseEntities.BaseApiTest;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import models.projectModels.GetResponseResult;
import models.projectModels.PostResponseResult;
import models.suitesModels.SuiteResponseResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.LoginStep;

public class DeleteSuiteProject extends BaseApiTest {

    protected int suiteId;

    @Test
    @Description("Login with incorrect 'Email' field value")
    public void loginWithIncorrectEmailFieldValue() {

        String loginErrorMessage = "These credentials do not match our records.";
        String incorrectLogin = RandomStringUtils.randomAlphanumeric(7) + "@" + RandomStringUtils.randomAlphabetic(5) + ".com";
        String incorrectPSW = RandomStringUtils.randomAlphanumeric(8);

        new LoginStep()
                .incorrectLogin(incorrectLogin, incorrectPSW)
                .getCredentialsDoNotMatchMessage()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText(loginErrorMessage));
    }

    @Test
    public void createProjectsTest(){
        PostResponseResult actProject = new ProjectsAdapter().postCreateProject(expProject);
        Assert.assertEquals(actProject.getResult().getCode(), expProject.getCode().toUpperCase());
    }

    @Test(dependsOnMethods = "createProjectsTest")
    public void postCreateTestSuite() {
        SuiteResponseResult suite = new SuitesAdapter().postCreateSuite(expSuite, projectCode.toUpperCase());
        suiteId = suite.getResult().getId();
    }

    @Test(dependsOnMethods = "postCreateTestSuite")
    public void deleteSuite() {
        PostResponseResult suiteDel = new SuitesAdapter().deleteSuite(projectCode.toUpperCase(), suiteId);
        Assert.assertTrue(suiteDel.isStatus());
    }

    @Test(dependsOnMethods = "deleteSuite")
    public void deleteProject() {
        GetResponseResult projectDel = new ProjectsAdapter().deleteProject(projectCode.toUpperCase());
        System.out.println(projectDel);
    }
}
