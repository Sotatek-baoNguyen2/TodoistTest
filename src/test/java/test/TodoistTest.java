package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import api.ProjectApi;
import api.TaskApi;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import model.Config;
import model.Task;
import page.AddTaskPage;
import page.MenuPage;
import page.TaskPage;
import page.LoginEmailPage;
import page.LoginPage;
import page.DefautPage;
public class TodoistTest {
	TaskApi taskApi;
	ProjectApi projectApi;
	AppiumDriver<MobileElement> driver;
	WebDriverWait wait;
	String id = "";
	List<Task> lists;
	LoginPage loginPage;
	LoginEmailPage loginEmail;
	TaskPage taskPage;
	MenuPage menuPage;
	AddTaskPage addTaskPage;
	DefautPage defautPage;
	Config config;
	String nameProject = "Project test 3";
	String nameTask = "Task test";

	@BeforeMethod
	public void setup() throws MalformedURLException {
		taskApi = new TaskApi();
		projectApi = new ProjectApi();
		DesiredCapabilities des = new DesiredCapabilities();
		des.setCapability("deviceName", "Pixel 3a API 30");
		des.setCapability("udid", "emulator-5554");
		des.setCapability("platformName", "Android");
		des.setCapability("platformVersion", "11.0.0");
		des.setCapability("appPackage", "com.todoist");
		des.setCapability("appActivity", "com.todoist.activity.HomeActivity");

		URL url = new URL("http://127.0.0.1:4723/wd/hub");

		driver = new AppiumDriver<MobileElement>(url, des);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		loginPage = new LoginPage(driver);
		loginEmail = new LoginEmailPage(driver);
		taskPage = new TaskPage(driver);
		menuPage = new MenuPage(driver);
		addTaskPage = new AddTaskPage(driver);
		defautPage =new DefautPage(driver);
		
		

	}

	// create project
	@Test
	void testCreateProject() throws Exception {

		// Create with API
		projectApi.createProject(nameProject);
		
		// verify create project);
		loginPage.clickLoginEmail();
		loginEmail.login(Config.email, Config.password);
		defautPage.clickMenu();
		menuPage.clickExpandProject();
		if (menuPage.checkProjectName(nameProject)) {
			System.out.println("Create Project success");
		}

		// create task
		menuPage.clickProject(nameProject);
		taskPage.clickBtnAddTask();
		addTaskPage.inputName(nameTask);
		addTaskPage.clickCreateTask();
		driver.navigate().back();
		Thread.sleep(3000);

		//get Task's id
		id = taskApi.getTaskId(nameTask);
		System.out.println(id);
		taskPage.clickCompleteTask();	
		
		//Reopen task api
		taskApi.reOpenTask(id);
		
		driver.navigate().back();
		taskPage.clickMenu();
		menuPage.clickProject(nameProject);
		taskPage.refresh();
		// check reopen success
		if (taskPage.isDisplayTaskName(nameTask)) {
			System.out.println("Reopen success!");
		}
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
