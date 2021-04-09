package api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

import java.util.List;

import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Config;
import model.Task;

public class TaskApi {
	
	public Response getAllTask(String taskName) {
		Response res = given().header("Authorization", "Bearer " + Config.token).when()
				.get("https://api.todoist.com/rest/v1/tasks").then().contentType(ContentType.JSON).statusCode(200)
				.body("content", hasItems(taskName)).extract().response();
		return res;
	}

	public void reOpenTask(String id) {
		given().header("Authorization", "Bearer " + Config.token).when()
				.post("https://api.todoist.com/rest/v1/tasks/{id}/reopen", id).then().statusCode(204).log().all();
	}
	
	public String getTaskId(String taskName) {	
		String id ="";
		Response res = given().header("Authorization", "Bearer " + Config.token).when()
				.get("https://api.todoist.com/rest/v1/tasks").then().contentType(ContentType.JSON).statusCode(200)
				.body("content", hasItems(taskName)).extract().response();
		List<Task> lists;
		lists = res.jsonPath().getList("$", Task.class);
		for (Task task : lists) {
			if (task.content.equalsIgnoreCase(taskName)) {
				id = task.id;
				return id;
			}
		}
		return id;
	}
	@Test
	public void getAnTask() {
		Response res = given().header("Authorization", "Bearer " + Config.token).when()
				.get("https://api.todoist.com/rest/v1/tasks/4729176986").then().contentType(ContentType.JSON).statusCode(200).log().body()
				.extract().response();;		
            System.out.println(res.jsonPath().getString("id"));
	}
	@Test
	public void getAllTasks() {
		Response res = given().header("Authorization", "Bearer " + Config.token).when()
				.get("https://api.todoist.com/rest/v1/tasks").then().contentType(ContentType.JSON).statusCode(200).log().all()
				.extract().response();
	}
}
