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

public class ProjectCreateCorrectNameBoundaryTest extends BaseTest {

    Logger LOGGER = LoggerFactory.getLogger(ProjectCreateCorrectNameBoundaryTest.class);
    ProjectPage projectPage;
    ProjectsPage projectsPage;

    @Test
    @Description("Create project with name of 255 symbols")
    public void createProjectWithCorrectNameLengthTest() {

        String randomProjectName = RandomStringUtils.randomAlphanumeric(255);

        projectsPage = new LoginPage(true)
                .setEmail(ReadProperties.getInstance().getUsername())
                .setPassword(ReadProperties.getInstance().getPassword())
                .successLoginBtnClick();

        projectPage = projectsPage
                .createProjectButtonClick()
                .setProjectName(randomProjectName)
                .clickCreateProjectSuccessBtn();

        LOGGER.error(String.format(
                "Expected Project name is '%s' and was '%s'",
                randomProjectName,
                projectPage.getProjectNameHeader().getText()
        ));
        projectPage.getProjectNameHeader().shouldHave(Condition.exactText(randomProjectName));
    }
}
