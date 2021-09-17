package steps;

import endpoints.UiEndpoints;
import pages.ProjectPage;
import pages.ProjectsPage;

public class DeleteProjectStep {
    public ProjectsPage deleteProject(String projectCode) {

        new ProjectPage(false, String.format(UiEndpoints.PROJECT, projectCode))
                .clickSettingsButton(projectCode)
                .clickDeleteProjectButton(projectCode)
                .clickDeleteConfirmationButton(projectCode);
        return new ProjectsPage(false, String.format(UiEndpoints.PROJECT, projectCode));
    }
}
