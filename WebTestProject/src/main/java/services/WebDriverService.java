package services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverService {
	private final String driverName = "webdriver.chrome.driver";
	private final String driverPath = "src//main//resources//chromedriver.exe";
	private WebDriver chromeDriver;
	private LogService logService;
	
	public WebDriverService(LogService logService) {
		this.logService = logService;
	}
	
	public void initChromDriver() {
		logService.printToConsoleAndToLog("INFO: starting "+driverName);
		System.setProperty(driverName, driverPath);
		chromeDriver = new ChromeDriver();		
		chromeDriver.manage().window().maximize();
		chromeDriver.manage().deleteAllCookies();
		logService.printToConsoleAndToLog("INFO:"+driverName+" started");
	}
	
	public WebDriver getChromeDriver(){
		return chromeDriver;
	}
}
