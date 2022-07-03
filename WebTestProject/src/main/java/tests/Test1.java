package tests;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.LoginPage;
import pages.MainPage;
import services.ConfigValues;
import services.LogService;

public class Test1 {
	
	private Properties props;
	private WebDriver driver;
	private LogService logService;
	public Test1(Properties props, WebDriver driver, LogService logService) {
		this.props = props;
		this.driver = driver;
		this.logService = logService;
	}

	
	public void runTests() {
		LoginPage loginPage = new LoginPage(props.getProperty(ConfigValues.pageLoginUrlPr),
				props.getProperty(ConfigValues.userPr),
				props.getProperty(ConfigValues.passwordPr), driver, logService);
		MainPage mainPage = new MainPage(driver, logService);
		try {
			if(!loginPage.loginTest()) {
				logService.printToConsoleAndToLog("ERROR: loginPage.loginTest() test failed");
				return;
			}
			else {
				logService.printToConsoleAndToLog("SUCCESS: loginPage.loginTest() test finished successfully");
			}
		}
		catch (Exception e) {
			logService.printToConsoleAndToLog("ERROR: loginPage.login test failed:"+e.toString());
			return;
		}
		try {
			if(!mainPage.do1Do2ButtonsTest()) {
				logService.printToConsoleAndToLog("ERROR: mainPage.do1Do2ButtonsTest test failed");
			}
			else {
				logService.printToConsoleAndToLog("SUCCESS: mainPage.do1Do2ButtonsTest test finished successfully");
			}
		}
		catch (Exception e) {
			logService.printToConsoleAndToLog("ERROR: mainPage.do1Do2ButtonsTest test failed:"+e.toString());
		}
		try {
			if(!mainPage.fontSizeChangeTest()) {
				logService.printToConsoleAndToLog("ERROR: mainPage.fontSizeChangeTest test failed");
			}
			else {
				logService.printToConsoleAndToLog("SUCCESS: mainPage.fontSizeChangeTest test finished successfully");
			}
		}
		catch (Exception e) {
			logService.printToConsoleAndToLog("ERROR: mainPage.fontSizeChangeTest test failed:"+e.toString());
		}
		try {
			if(!mainPage.backGroundColorChangeTest()) {
				logService.printToConsoleAndToLog("ERROR: mainPage.backGroundColorChangeTest test failed");
			}
			else {
				logService.printToConsoleAndToLog("SUCCESS: mainPage.backGroundColorChangeTest test finished successfully");
			}
		}
		catch (Exception e) {
			logService.printToConsoleAndToLog("ERROR: mainPage.backGroundColorChangeTest test failed:"+e.toString());
		}

	}

}
