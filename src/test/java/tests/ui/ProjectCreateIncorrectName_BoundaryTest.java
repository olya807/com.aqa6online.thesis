package tests.ui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import core.ReadProperties;
import endpoints.UiEndpoints;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProjectCreatePage;
import pages.ProjectsPage;

public class ProjectCreateIncorrectName_BoundaryTest extends BaseTest {

    ProjectsPage projectsPage;
    ProjectCreatePage projectCreatePage;

    @Test
    @Description("Create project with name of more than 255 symbols")
    public void createProjectWithIncorrectNameLengthTest() {

        String randomProjectName = RandomStringUtils.randomAlphanumeric(256);
        String projectNameErrorMessage = "The title may not be greater than 255 characters.";

        projectsPage = new LoginPage(true, UiEndpoints.LOGIN)
                .setEmail(ReadProperties.getInstance().getUsername())
                .setPassword(ReadProperties.getInstance().getPassword())
                .successLoginBtnClick();

        projectCreatePage = projectsPage
                .createProjectButtonClick()
                .setProjectName(randomProjectName)
                .clickCreateProjectBtn();

        projectCreatePage.getProjectNameErrorMessage().shouldHave(Condition.exactText(projectNameErrorMessage));
    }
}
