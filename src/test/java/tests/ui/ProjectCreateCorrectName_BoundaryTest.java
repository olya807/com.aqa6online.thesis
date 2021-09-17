package tests.ui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import pages.ProjectPage;
import steps.LoginStep;
import steps.CreateProjectStep;
import steps.DeleteProjectStep;

import static com.codeborne.selenide.Condition.visible;

public class ProjectCreateCorrectName_BoundaryTest extends BaseTest {

    Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    ProjectPage projectPage;


    @Test
    @Description("Create project with name of 255 symbols")
    public void createProjectWithCorrectNameLengthTest() {

        String randomProjectName = RandomStringUtils.randomAlphanumeric(255);
        String randomProjectCode = RandomStringUtils.randomAlphanumeric(6);

        new LoginStep()
                .correctLogin();

        new CreateProjectStep()
                .createProject(randomProjectName,randomProjectCode)
                .getProjectNameHeader().shouldHave(Condition.exactText(randomProjectName));

        LOGGER.error(String.format(
                "Expected Project name is '%s'",
                randomProjectName));

        new DeleteProjectStep()
                .deleteProject(randomProjectCode)
                .fillProjectSearchInput(randomProjectName)
                .noProjectMessage()
                .shouldBe(Condition.not(visible))
                .shouldHave(Condition.exactText("Looks like you don’t have any projects yet."));
    }



    }

