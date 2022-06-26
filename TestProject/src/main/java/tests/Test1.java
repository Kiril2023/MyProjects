package tests;

import org.openqa.selenium.WebDriver;

import pages.DatePage;
import pages.DestinationPage;
import pages.TravelPolicyPage;
import services.LogService;

public class Test1 {

	private WebDriver driver;
	private LogService logService;
	public Test1(WebDriver driver, LogService logService) {
		this.driver = driver;
		this.logService = logService;
	}
	
	public void runTests() {

		TravelPolicyPage travelPolicyPage = new TravelPolicyPage(driver, logService);
		DestinationPage destinationPage = new DestinationPage(driver, logService);
		DatePage datePage = new DatePage(driver, logService);
		try {
			if(!travelPolicyPage.clickOnFirstPurchase()) {
				logService.printToConsoleAndToLog("ERROR: travelPolicyPage.clickOnFirstPurchase() test failed");
				return;
			}
			else {
				logService.printToConsoleAndToLog("SUCCESS: travelPolicyPage.clickOnFirstPurchase() test finished successfully");
			}
		}
		catch (Exception e) {
			logService.printToConsoleAndToLog("ERROR: travelPolicyPage.clickOnFirstPurchase() test failed:"+e.toString());
			return;
		}
		
		try {
			if(!destinationPage.clickOnEurope()) {
				logService.printToConsoleAndToLog("ERROR: destinationPage.clickOnEurope() test failed");
				return;
			}
			else {
				logService.printToConsoleAndToLog("SUCCESS: destinationPage.clickOnEurope() test finished successfully");
			}
		}
		catch (Exception e) {
			logService.printToConsoleAndToLog("ERROR: destinationPage.clickOnEurope() test failed:"+e.toString());
			return;
		}
		
		try {
			if(!datePage.chooseDates()) {
				logService.printToConsoleAndToLog("ERROR: datePage.chooseDates() test failed");
				return;
			}
			else {
				logService.printToConsoleAndToLog("SUCCESS: datePage.chooseDates() test finished successfully");
			}
		}
		catch (Exception e) {
			logService.printToConsoleAndToLog("ERROR: datePage.chooseDates() test failed:"+e.toString());
			return;
		}
	}
}
