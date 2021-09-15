package tests.ui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import core.ReadProperties;
import endpoints.UiEndpoints;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import pages.CasePage;
import pages.LoginPage;
import pages.ProjectPage;
import pages.ProjectsPage;

import static com.codeborne.selenide.Condition.visible;

public class TestCase_CRUDTest extends BaseTest {

    ProjectPage projectPage;
    ProjectsPage projectsPage;
    final String randomProjectName = RandomStringUtils.randomAlphanumeric(20);
    final String randomProjectCode = RandomStringUtils.randomAlphabetic(6).toUpperCase();

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

        projectPage
                .getProjectNameHeader()
                .shouldHave(Condition.exactText(randomProjectName));
    }


    @Test(dependsOnMethods = "createProjectTest")
    @Description("Create test case")
    public void testCaseCreateTest() {

        CasePage casePage = new CasePage(true, String.format(UiEndpoints.CASE_CREATE, randomProjectCode));
        casePage
                .fillCaseForm(testCaseBuilder)
                .clickSaveButton()
                .alertMessageCaseCreated()
                .shouldBe(visible)
                .shouldHave(Condition.exactText("Test case was created successfully!"));
    }

    @Test(dependsOnMethods = "testCaseCreateTest")
    @Description("Update test case")
    public void testCaseUpdateTest() {
        projectPage
                .getTestCaseHeader(testCaseBuilder.getTitle(), randomProjectCode)
                .clickEditButton(randomProjectCode)
                .updateCase(testCase2Builder)
                .clickSaveButton()
                .alertMessageCaseEdited()
                .shouldBe(visible)
                .shouldHave(Condition.exactText("Test case was edited successfully!"));
    }

    @Test(dependsOnMethods = "testCaseUpdateTest")
    @Description("Delete test case")
    public void testCaseDeleteTest() {
        projectPage
                .getTestCaseHeader(testCaseBuilder.getTitle(), randomProjectCode)
                .clickDeleteButton(randomProjectCode)
                .clickDeleteConfirmationButton(randomProjectCode)
                .alertMessageCaseDeleted()
                .shouldBe(visible)
                .shouldHave(Condition.text(String.format("Test case ['%s'-'1'] was successfully deleted", randomProjectCode)));
    }

    @Test(dependsOnMethods = "testCaseDeleteTest")
    @Description("Delete project")
    public void projectDeleteTest() {

        projectPage
                .clickSettingsButton(randomProjectCode)
                .clickDeleteProjectButton(randomProjectCode)
                .clickDeleteConfirmationButton(randomProjectCode)
                .fillProjectSearchInput(randomProjectName)
                .noProjectMessage()
                .shouldBe(Condition.not(visible))
                .shouldHave(Condition.exactText("Looks like you don’t have any projects yet."));
    }
}
