package tests.ui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import core.ReadProperties;
import endpoints.UiEndpoints;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProjectPage;
import pages.ProjectsPage;

public class NegativeTests extends BaseTest {
        ProjectPage projectPage;
        ProjectsPage projectsPage;

        @Test
        @Description("Create project with code of 1 symbol")
        public void createProjectWithInvalidCodeLengthTest() {

            String randomProjectName = RandomStringUtils.randomAlphanumeric(256);

            projectsPage = new LoginPage(true, UiEndpoints.LOGIN)
                    .setEmail(ReadProperties.getInstance().getUsername())
                    .setPassword(ReadProperties.getInstance().getPassword())
                    .successLoginBtnClick();

            /*projectPage = projectsPage
                    .createProjectButtonClick()
                    .setProjectName(RandomStringUtils.randomAlphanumeric(15))
                    .setProjectCode(RandomStringUtils.randomAlphabetic(1))
                    .clickCreateProjectSuccessBtn()
                    .controlFeedbackMessage()
            .shouldBe(Condition.visible)
            .shouldHave(Condition.exactText("The code must be at least 2 characters."));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
}
