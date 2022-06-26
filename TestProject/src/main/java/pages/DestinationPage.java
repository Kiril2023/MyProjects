package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import services.LogService;

public class DestinationPage {

	public final static String PAGE_URL = "https://digital.harel-group.co.il/travel-policy/wizard/destination";
	public final static String EUROPE_BUTTON_XPATH = "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[1]/div[2]/div/div[4]/div";
	private final long SLEEP_MILLISEONCDS = 5000;
	private WebElement europeButton;
	private LogService  logService;
	private WebDriver driver;
	
	
	public DestinationPage(WebDriver driver, LogService logService) {
		this.driver = driver;
		this.logService = logService;
	}
	public boolean clickOnEurope() throws InterruptedException {
		boolean result = true;
		logService.printToConsoleAndToLog("INFO: Starting test of clickOnEurope on:"+PAGE_URL);
		driver.get(PAGE_URL);
		initPageElements();
		europeButton.click();
		 Thread.sleep(SLEEP_MILLISEONCDS);
		if(DatePage.PAGE_URL.equals(driver.getCurrentUrl())) {
			logService.printToConsoleAndToLog("SUCCES: URL after button click is as expected: "+DatePage.PAGE_URL);
		}
		else {
			logService.printToConsoleAndToLog("ERROR: URL after button click is NOT as expected: "+DatePage.PAGE_URL);
			result = false;
		}
		return result;
		
	}
	
	private void initPageElements(){
		setFirstPurchaseButtonByName();
	}
	
	private void setFirstPurchaseButtonByName() {
		this.europeButton = driver.findElement(By.xpath(EUROPE_BUTTON_XPATH));
	}
}

