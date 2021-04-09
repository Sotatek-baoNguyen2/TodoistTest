package page;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MenuPage extends CommonBase {
	AppiumDriver<MobileElement> driver;

	public MenuPage(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	@AndroidFindBy(xpath = "//*[@text='Projects']")
	MobileElement btnExpandProject;

	@AndroidFindBy(className = "Add project")
	MobileElement btnAddProject;

	@AndroidFindBy(accessibility = "Add")
	MobileElement btnAdd;

	public void clickExpandProject() {
		Click(btnExpandProject, driver);
	}

	public void clickProject(String name) {
		String xpath = String.format("//android.widget.TextView[@resource-id='com.todoist:id/name' and @text='%s']", name);
		MobileElement btnProject = driver.findElement(By.xpath(xpath));
		Click(btnProject, driver);
	}

	public void clickAddProject() {
		Click(btnAddProject, driver);
	}

	public void clickBtnAdd() {
		Click(btnAdd, driver);
	}

	public boolean checkProjectName(String name) {
		String xpath = String.format("//android.widget.TextView[@resource-id='com.todoist:id/name' and @text='%s']", name);
		MobileElement btnProject = driver.findElement(By.xpath(xpath));
		return btnProject.isDisplayed();
	}
}
