package page;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TaskPage extends CommonBase {
	AppiumDriver<MobileElement> driver;

	public TaskPage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(accessibility = "Complete")
	MobileElement checkBoxComplete;
	
	@AndroidFindBy(id="com.todoist:id/fab")
	MobileElement btnAddTask;

	@AndroidFindBy(accessibility = "Change the current view")
	MobileElement menuButton;
	
	public void clickCompleteTask() {
		Click(checkBoxComplete, driver);
	}
	public void clickBtnAddTask() {
		Click(btnAddTask, driver);
	}
	public void clickMenu() {
		Click(menuButton, driver);
	}
	public void refresh() {
		refreshApp(driver);
	}
	
	public boolean isDisplayTaskName(String name) {
		String xpath = String.format("//android.widget.TextView[@resource-id='com.todoist:id/text' and @text='%s']", name);
		MobileElement textTask = driver.findElement(By.xpath(xpath));
		return textTask.isDisplayed();
	}
}
