package page;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends CommonBase {
	public AppiumDriver<MobileElement> driver;

	public LoginPage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}

	@AndroidFindBy(id="com.todoist:id/btn_welcome_continue_with_email")
	MobileElement loginEmail;
	@AndroidFindBy(id="com.todoist:id/btn_google")
	MobileElement loginGoogle;
	@AndroidFindBy(id="com.todoist:id/btn_facebook")
	MobileElement loginFacebook ;
	
	public void clickLoginEmail() {
		Click(loginEmail, driver);
	}
	
	public void clickLoginGmail() {
		Click(loginGoogle, driver);
	}
	
	public void clickLoginFacebook() {
		Click(loginFacebook, driver);
	}
}
