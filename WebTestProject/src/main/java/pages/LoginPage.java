package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import services.LogService;

public class LoginPage {

	private final String USER_TEXT_BOX_ELEMENT_NAME = "username";
	private final String PASSWORD_TEXT_BOX_ELEMENT_NAME = "password";
	private final String LOGIN_BUTTON_ELEMENT_NAME = "login";	
	private String url;
	private String user;
	private String pass;
	private WebDriver driver;
	private WebElement userTextBoxElement;
	private WebElement passwordTextBoxElement;
	private WebElement logintButtonElement;
	private LogService  logService;
	
	public LoginPage(String url, String user, String pass, WebDriver driver, LogService logService) {
		this.url = url;
		this.user = user;
		this.pass = pass;
		this.driver = driver;
		this.logService = logService;
	}
	
	public boolean loginTest() {
		boolean result = true;
		logService.printToConsoleAndToLog("INFO: Starting test of loginPage.loginTest to:"+url);
		driver.get(url);
		initPageElements();
		userTextBoxElement.sendKeys(user);
		passwordTextBoxElement.sendKeys(pass);
		logintButtonElement.click();
		if(MainPage.getUrl().equals(driver.getCurrentUrl())) {
			logService.printToConsoleAndToLog("SUCCES: URL after login is as expected");
		}
		else {
			result = false;
		}
		return result;

	}
	
	private void setUserTextBoxElementByName() {
		this.userTextBoxElement = driver.findElement(By.name(USER_TEXT_BOX_ELEMENT_NAME));
	}
	
	private void setPasswordTextBoxElemenBytName() {
		this.passwordTextBoxElement = driver.findElement(By.name(PASSWORD_TEXT_BOX_ELEMENT_NAME));
	}
	
	private void setLogintButtonElementByName() {
		this.logintButtonElement = driver.findElement(By.name(LOGIN_BUTTON_ELEMENT_NAME));
	}
	
	private void initPageElements(){
		setUserTextBoxElementByName();
		setPasswordTextBoxElemenBytName();
		setLogintButtonElementByName();
	}
	
}
