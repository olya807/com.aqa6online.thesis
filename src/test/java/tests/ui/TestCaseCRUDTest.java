package tests.ui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import core.ReadProperties;
import endpoints.UiEndpoints;
import io.qameta.allure.Description;
import models.ui.TestCase;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CasePage;
import pages.LoginPage;
import pages.ProjectPage;
import pages.ProjectsPage;

import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;

public class TestCaseCRUDTest extends BaseTest {

    ProjectPage projectPage;
    ProjectsPage projectsPage;
    String randomProjectName = RandomStringUtils.randomAlphanumeric(20);
    String randomTestCaseName = RandomStringUtils.randomAlphanumeric(15);
    String randomProjectCode = RandomStringUtils.randomAlphabetic(6).toUpperCase(Locale.ROOT);
    String updatedPreconds = RandomStringUtils.randomAlphanumeric(15);
    TestCase testCase = TestCase.builder()
            .title(randomTestCaseName)
            .status("Actual")
            .description(RandomStringUtils.randomAlphabetic(10))
            .suite("Test cases without suite")
            .severity("Critical")
            .priority("High")
            .type("Smoke")
            .layer("Unit")
            .isFlaky("No")
            .milestone("Not set")
            .behavior("Not set")
            .automationStatus("Not automated")
            .preconditions(RandomStringUtils.randomAlphabetic(50))
            .postconditions(RandomStringUtils.randomAlphanumeric(50))
            .build();

    TestCase testCase2 = TestCase.builder()
            .layer("API")
            .preconditions(updatedPreconds)
            .build();

    @Test
    @Description("Create project with correct name")
    public void createProjectTest() {

        projectsPage = new LoginPage(true, UiEndpoints.LOGIN)
                .setEmail(ReadProperties.getInstance().getUsername())
                .setPassword(ReadProperties.getInstance().getPassword())
                .successLoginBtnClick();

        projectPage = projectsPage
                .createProjectButtonClick()
                .setProjectName(randomProjectName)
                .setProjectCode(randomProjectCode)
                .clickCreateProjectSuccessBtn();

        projectPage.getProjectNameHeader().shouldHave(Condition.exactText(randomProjectName));
    }
//divide into logical steps

    @Test
    @Description("Create test case")
    public void testCaseCRUDTest() throws InterruptedException {

        CasePage casePage = new CasePage(true, String.format(UiEndpoints.CASE_CREATE, randomProjectCode));
        casePage
                .fillCaseForm(testCase)
                .clickSaveButton()
                .alertMessageCaseCreated()
                .shouldBe(visible)
                .shouldHave(Condition.exactText("Test case was created successfully!"));

        projectPage
                .getTestCaseHeader(randomTestCaseName, randomProjectCode)
                .clickEditButton(randomProjectCode)
                .updateCase(testCase2)
                .clickSaveButton()
                .alertMessageCaseEdited()
                .shouldBe(visible)
                .shouldHave(Condition.exactText("Test case was edited successfully!"));
        projectPage
                .getTestCaseHeader(randomTestCaseName, randomProjectCode)
                .clickDeleteButton(randomProjectCode)
                .clickDeleteConfirmationButton(randomProjectCode)
                .alertMessageCaseDeleted()
                .shouldBe(visible);//добавить проверку по тексту

        projectPage
                .clickSettingsButton(randomProjectCode)
                .clickDeleteProjectButton(randomProjectCode)
                .clickDeleteConfirmationButton(randomProjectCode)
                .noProjectMessage();
                //.shouldBe(Condition.not(visible))
                //.shouldHave(Condition.exactText("Looks like you don’t have any projects yet."));
    }
}
