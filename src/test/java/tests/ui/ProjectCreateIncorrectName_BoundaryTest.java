package tests.ui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import endpoints.UiEndpoints;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import pages.ProjectCreatePage;
import pages.ProjectsPage;
import steps.CreateProjectStep;
import steps.LoginStep;

public class ProjectCreateIncorrectName_BoundaryTest extends BaseTest {

    ProjectsPage projectsPage;
    ProjectCreatePage projectCreatePage;

    @Test
    @Description("Create project with name of more than 255 symbols")
    public void createProjectWithIncorrectNameLengthTest() {

        String randomProjectName = RandomStringUtils.randomAlphanumeric(256);
        String projectNameErrorMessage = "The title may not be greater than 255 characters.";

        new LoginStep()
                .correctLogin();
        new CreateProjectStep()
                .incorrectProjectCreation(randomProjectName)
                .getProjectNameErrorMessage()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText(projectNameErrorMessage));
    }
}
