package tests.api;

import adapters.ProjectsAdapter;
import baseEntities.BaseApiTest;
import models.api.projectModels.GetResponseResult;
import models.api.projectModels.PostResponseResult;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetAllProjects extends BaseApiTest {
    @Test
    public void createProjectsTest() {
        PostResponseResult actProject = new ProjectsAdapter().postCreateProject(expProject);
        Assert.assertEquals(actProject.getResult().getCode(), expProject.getCode().toUpperCase());
    }

    @Test(dependsOnMethods = "createProjectsTest")
    public void getAllProjectsTest() {
        GetResponseResult projectsList = new ProjectsAdapter().getAllProjects();
        System.out.println(projectsList.getResult().getEntities().get(0).getCode());
    }

    @Test(dependsOnMethods = "getAllProjectsTest")
    public void deleteProject() {
        GetResponseResult projectDel = new ProjectsAdapter().deleteProject(projectCode.toUpperCase());
        System.out.println(projectDel);

    }
}
