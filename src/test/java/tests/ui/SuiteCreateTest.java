package tests.ui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import core.ReadProperties;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProjectPage;
import pages.ProjectsPage;

public class SuiteCreateTest extends BaseTest {

    Logger logger = LoggerFactory.getLogger(ProjectCreateCorrectNameTest.class);
    ProjectPage projectPage;
    ProjectsPage projectsPage;

    @Test
    @Description("Create project and check alert message")
    public void checkCreateProjectAlert() {

        String randomProjectName = RandomStringUtils.randomAlphanumeric(15);
        String projectCreatedSuccessMessage = String.format("Project \"%s\" was created successfully!" , randomProjectName);
        String projectCreatedAlreadyExistsMessage = "Project with the same code already exists.";

        projectsPage = new LoginPage(true)
                .setEmail(ReadProperties.getInstance().getUsername())
                .setPassword(ReadProperties.getInstance().getPassword())
                .successLoginBtnClick();

        projectPage = projectsPage
                .createProjectButtonClick()
                .setProjectName(randomProjectName)
                .clickCreateProjectSuccessBtn();

        projectPage
                .getAlertMessage()
                    .shouldHave(Condition.matchText(projectCreatedSuccessMessage));
    }

    @Test(dependsOnMethods = "checkCreateProjectAlert")
    @Description("Create suite and check alert message")
    public void checkCreateSuiteAlert() {

        String randomSuiteName = RandomStringUtils.randomAlphanumeric(10);
        String suiteCreatedSuccessMessage = "Suite was successfully created.";

        projectPage
                .clickCreateNewSuiteButton()
                .setSuiteName(randomSuiteName)
                .clickSaveSuiteButton()
                .getAlertMessage()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText(suiteCreatedSuccessMessage));
    }
}
