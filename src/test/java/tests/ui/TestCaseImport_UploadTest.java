package tests.ui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import endpoints.UiEndpoints;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CasePage;
import pages.ProjectPage;
import steps.LoginStep;
import steps.CreateProjectStep;

import java.io.File;

public class TestCaseImport_UploadTest extends BaseTest {

    String randomProjectName = RandomStringUtils.randomAlphanumeric(15);
    String randomProjectCode = RandomStringUtils.randomAlphabetic(6).toUpperCase();

    @Test
    @Description("Create project with correct name")
    public void createProjectTest() {

        new LoginStep()
                .correctLogin();
        new CreateProjectStep()
                .createProject(randomProjectName, randomProjectCode);
        Assert.assertEquals(new ProjectPage(false, String.format(UiEndpoints.PROJECT, randomProjectCode)).getProjectNameHeader().getText(), randomProjectName);
    }


    @Test
    @Description("Upload test case file")
    public void uploadTestCaseFileTest() {

        File file = new File("src/test/java/files/testrail.csv");

        CasePage casePage = new CasePage(true, String.format(UiEndpoints.CASE_IMPORT, randomProjectCode));
        casePage
                .getFileUploadField()
                .uploadFile(file);

        boolean isFileUploaded = casePage
                .getFileUploadFieldValue()
                .contains("testrail.csv");
        casePage
                .submitBtnClick()
                .getAlertMessage()
                .shouldHave(Condition.exactText("0 suites and 0 cases were successfully imported!"));

        Assert.assertTrue(isFileUploaded);
    }
}
