package steps;

import endpoints.UiEndpoints;
import pages.ProjectCreatePage;
import pages.ProjectPage;
import pages.ProjectsPage;

public class CreateProjectStep {
    ProjectPage projectPage;

    public ProjectPage createProject(String projectName, String projectCode) {

        projectPage = new ProjectsPage(false, UiEndpoints.PROJECTS)
                .createProjectButtonClick()
                .setProjectName(projectName)
                .setProjectCode(projectCode)
                .clickCreateProjectSuccessBtn();
        return projectPage = new ProjectPage(false,String.format(UiEndpoints.PROJECT,projectCode));
    }

    public ProjectCreatePage incorrectProjectCreation(String projectName) {

        ProjectsPage projectsPage = new ProjectsPage(false, UiEndpoints.PROJECTS);
        projectsPage
                .createProjectButtonClick()
                .setProjectName(projectName)
                .clickCreateProjectBtn();
        return new ProjectCreatePage(false,UiEndpoints.CREATE_PROJECT);
    }
}
