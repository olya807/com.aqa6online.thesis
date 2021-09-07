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

public class ProjectCreatedAlertTest extends BaseTest {

    Logger LOGGER = LoggerFactory.getLogger(ProjectCreateCorrectNameBoundaryTest.class);
    ProjectPage projectPage;
    ProjectsPage projectsPage;

    @Test
    @Description("Create project and check alert message")
    public void checkCreateProjectAlert() {

        String randomProjectName = RandomStringUtils.randomAlphanumeric(15);
        String projectCreatedSuccessMessage = String.format("Project \"%s\" was created successfully!", randomProjectName);

        projectsPage = new LoginPage(true)
                .setEmail(ReadProperties.getInstance().getUsername())
                .setPassword(ReadProperties.getInstance().getPassword())
                .successLoginBtnClick();

        projectPage = projectsPage
                .createProjectButtonClick()
                .setProjectName(randomProjectName)
                .clickCreateProjectSuccessBtn();

        LOGGER.error(String.format(
                "Expected Alert text is '%s and was '%s''",
                projectCreatedSuccessMessage,
                projectPage.getAlertMessage().getText()
        ));
        projectPage
                .getAlertMessage()
                    .shouldHave(Condition.matchText(projectCreatedSuccessMessage));
    }
}
