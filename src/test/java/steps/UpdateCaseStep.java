package steps;

import endpoints.UiEndpoints;
import models.TestCase;
import pages.ProjectPage;

public class UpdateCaseStep {

    public ProjectPage updateCase(String randomProjectCode, String randomTestCaseTitle, TestCase testCase2) {
        ProjectPage projectPage = new ProjectPage(false, String.format(UiEndpoints.PROJECT, randomProjectCode));
        projectPage
                .getTestCaseHeader(randomTestCaseTitle, randomProjectCode)
                .clickEditButton(randomProjectCode)
                .updateCase(testCase2)
                .clickSaveButton();
        return new ProjectPage(false, String.format(UiEndpoints.PROJECT, randomProjectCode));
    }
}