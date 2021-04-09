package page;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AddTaskPage extends CommonBase {
	AppiumDriver<MobileElement> driver;
	
	public AddTaskPage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}

	@AndroidFindBy(id="android:id/message")
	MobileElement inputNameTask;
	
	@AndroidFindBy(accessibility = "Add")
	MobileElement btnCreateTask;
	
	public void inputName(String name) {
		Input(inputNameTask, driver, name);
	}
	
	public void clickCreateTask() {
		Click(btnCreateTask, driver);
	}
}
