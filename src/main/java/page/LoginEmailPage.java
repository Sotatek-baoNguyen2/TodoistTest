package page;

import java.io.UnsupportedEncodingException;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginEmailPage extends CommonBase {
	AppiumDriver<MobileElement> driver;
	
	public LoginEmailPage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	@AndroidFindBy(id="com.todoist:id/email_exists_input")
	MobileElement emailInput;
	
	@AndroidFindBy(id="com.todoist:id/btn_continue_with_email")
	MobileElement btnContinue;
	
	@AndroidFindBy(id="com.todoist:id/log_in_password")
	MobileElement passwordInput;
	
	@AndroidFindBy(id="com.todoist:id/btn_log_in")
	MobileElement btnLogin;
	
	public void inputEmail(String email) {
		Input(emailInput, driver, email);
	}
	
	public void inputPassword(String password) {
		Input(passwordInput, driver, password);
	}
	
	public void clickBtnContinue() {
		Click(btnContinue, driver);
	}
	
	public void clickBtnLogin() {
		Click(btnLogin, driver);
	}
	
	public void login(String email, String password) throws UnsupportedEncodingException {
		inputEmail(email);
		clickBtnContinue();
		inputPassword(decrypt(password));
		clickBtnLogin();
	}
}
