package adapters;

import baseEntities.BaseAdapter;
import com.google.gson.reflect.TypeToken;
import api_endpoints.ProjectsEndpoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Project;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Projects_adapter extends BaseAdapter {

    public Project postCreateProject(Project project) {
        Response response = given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(ProjectsEndpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Project.class);
    }


    public List<Project> getAllProjects() {
        Response response = given()
                .when()
                .get(ProjectsEndpoints.GET_ALL_PROJECTS)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), new TypeToken<List<Project>>() {
        }.getType());
    }
}