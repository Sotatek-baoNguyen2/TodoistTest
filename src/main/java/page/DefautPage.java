package page;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class DefautPage extends CommonBase{
	AppiumDriver<MobileElement> driver;

	public DefautPage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(accessibility = "Change the current view")
	MobileElement menuButton;
	
	public void clickMenu() {
		Click(menuButton, driver);
	}
}
