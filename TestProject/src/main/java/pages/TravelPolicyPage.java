package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import services.LogService;

public class TravelPolicyPage {
	
	public final static String PAGE_URL = "https://digital.harel-group.co.il/travel-policy";
	public final String FIRST_PURCHASE_BUTTON_XPATH = "//*[@id=\"root\"]/div/div/div[1]/div[1]/div/div/div[1]/div/button/span";
	private final long SLEEP_MILLISEONCDS = 5000;
	private WebElement firstPurchaseButton;
	private LogService  logService;
	private WebDriver driver;
	
	public TravelPolicyPage(WebDriver driver, LogService logService) {
		this.driver = driver;
		this.logService = logService;
	}
	
	public boolean clickOnFirstPurchase() throws InterruptedException {
		boolean result = true;
		logService.printToConsoleAndToLog("INFO: Starting test of clickOnFirstPurchase on:"+PAGE_URL);
		driver.get(PAGE_URL);
		initPageElements();
		firstPurchaseButton.click();
		 Thread.sleep(SLEEP_MILLISEONCDS);
		if(DestinationPage.PAGE_URL.equals(driver.getCurrentUrl())) {
			logService.printToConsoleAndToLog("SUCCES: URL after button click is as expected: "+DestinationPage.PAGE_URL);
		}
		else {
			logService.printToConsoleAndToLog("ERROR: URL after button click is NOT as expected: "+DestinationPage.PAGE_URL);
			result = false;
		}
		return result;
		
	}
	
	private void initPageElements(){
		setFirstPurchaseButtonByName();
	}
	
	private void setFirstPurchaseButtonByName() {
		this.firstPurchaseButton = driver.findElement(By.xpath(FIRST_PURCHASE_BUTTON_XPATH));
	}
	
	
}
