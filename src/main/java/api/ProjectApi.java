package api;

import static io.restassured.RestAssured.given;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import model.Config;

public class ProjectApi {
	public void createProject(String name) {
		JSONObject request = new JSONObject();
		request.put("name", name);

		given().body(request.toJSONString()).header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + Config.token).when()
				.post("https://api.todoist.com/rest/v1/projects").then().statusCode(200).log().all();
	}
	@Test
	public void getAllProject() throws UnsupportedEncodingException {
		given().header("Authorization", "Bearer " + Config.token).when()
				.get("https://api.todoist.com/rest/v1/projects").then().contentType(ContentType.JSON).statusCode(200)
				.log().all().extract().response();
	}
}
