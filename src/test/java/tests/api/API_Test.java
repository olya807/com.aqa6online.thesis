package tests.api;

import adapters.Projects_adapter;
import baseEntities.BaseApiTest;
import com.google.gson.GsonBuilder;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class API_Test extends BaseApiTest {

@Test
public void getAllProjectsTest(){
    Project projects = Project.builder()
            .title("")
            .code("")
            .build();
    List<Project> projectsList = new Projects_adapter().getAllProjects();
    System.out.println(new GsonBuilder().create().toJson(projectsList.get(0)));
}

@Test
public void createProjectsTest(){
    Project expProject = Project.builder()
            .title("At1hjk")
            .code("Adfgghgyt")
            .description("any description")
            .build();
    Project actProject = new Projects_adapter().postCreateProject(expProject);
    Assert.assertEquals(actProject.getCode(),expProject.getCode());

}
}
