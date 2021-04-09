package page;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class CommonBase {

	public void Click(MobileElement element, AppiumDriver<MobileElement> driver) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public void handleTimeoutVisible(MobileElement element, AppiumDriver<MobileElement> driver) {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void Input(MobileElement element, AppiumDriver<MobileElement> driver, String key) {
		handleTimeoutVisible(element, driver);
		element.clear();
		element.sendKeys(key);
	}
    public static String encrypt(String encryptData) throws UnsupportedEncodingException {
    	byte[] encodedBytes = Base64.getEncoder().encode(encryptData.getBytes(StandardCharsets.UTF_8));   
    	String encoder= new String(encodedBytes, "UTF-8");
    	return encoder;
    }
    public static String decrypt(String decryptData) throws UnsupportedEncodingException {
    	byte[] decodedBytes = Base64.getDecoder().decode(decryptData.getBytes(StandardCharsets.UTF_8));   	
    	String decoder= new String(decodedBytes, "UTF-8");
    	return decoder;
    }
	public void refreshApp(AppiumDriver<MobileElement> driver){
		TouchAction touchAction = new TouchAction(driver);
		Dimension dimension = driver.manage().window().getSize();
		double screenHeight = dimension.getHeight();
		double screenWidth = dimension.getWidth();
		touchAction
				.press(PointOption.point((int) (screenWidth * 0.5),(int) (screenHeight * 0.3)))
				.moveTo(PointOption.point((int) (screenWidth * 0.5),(int) (screenHeight * 0.6)))
				.release().perform();
	}
	
}
