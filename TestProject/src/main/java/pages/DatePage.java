package pages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import services.LogService;

public class DatePage {
	
	public final static String PAGE_URL = "https://digital.harel-group.co.il/travel-policy/wizard/date";
	private final String START_DATE_XPATH = "//*[@id=\"travel_start_date\"]";
	private final String END_DATE_XPATH = "//*[@id=\"travel_end_date\"]";
	private final String NEXT_BUTTON_XPATH = "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[3]/button[2]";
	private final String NUM_OF_TRIP_DAYS_XPATH = "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[2]/span";
	private final int START_DATE_DELAY = 7;
	private final int TRIP_LENGTH = 30;
	private final long SLEEP_MILLISEONCDS = 5000;
	private WebElement startDateElement;
	private WebElement endDateElement;
	private WebElement nextButtonElement;
	private WebElement numOfTripDaysElement;
	
	private LogService  logService;
	private WebDriver driver;
	public DatePage(WebDriver driver, LogService logService) {
		this.driver = driver;
		this.logService = logService;
	}
	
	public boolean chooseDates() throws InterruptedException {
		boolean result = true;
		logService.printToConsoleAndToLog("INFO: Starting test of chooseDates() on:"+PAGE_URL);
		driver.get(PAGE_URL);
		initPageElements();
		startDateElement.sendKeys(getFutureDate(START_DATE_DELAY));
		endDateElement.sendKeys(getFutureDate(TRIP_LENGTH+START_DATE_DELAY));
		Thread.sleep(SLEEP_MILLISEONCDS);
		setNumOfTripDaysElement();
		String tripDays = parseTipDays(numOfTripDaysElement.getText());
		logService.printToConsoleAndToLog("INFO: Trip days:"+tripDays);
		if(Integer.valueOf(tripDays)==TRIP_LENGTH+1) {
			logService.printToConsoleAndToLog("SUCCES: number of trip days is as expected: "+tripDays);
		}
		else {
			logService.printToConsoleAndToLog("NUMBER: number of trip days is: "+tripDays+" expected:"+TRIP_LENGTH);
			result = false;
		}


		nextButtonElement.click();
		if(TravelersPage.PAGE_URL.equals(driver.getCurrentUrl())) {
			logService.printToConsoleAndToLog("SUCCES: URL after button click is as expected: "+TravelersPage.PAGE_URL);
		}
		else {
			logService.printToConsoleAndToLog("ERROR: URL after button click is NOT as expected: "+TravelersPage.PAGE_URL);
			result = false;
		}
		return result;

	}
	
	
	private void initPageElements(){
		setStartDateElement();
		setEndDateElement();
		setNextButtonElement();
	}
	
	private void setStartDateElement() {
		this.startDateElement = driver.findElement(By.xpath(START_DATE_XPATH));
	}
	
	private void setEndDateElement() {
		this.endDateElement = driver.findElement(By.xpath(END_DATE_XPATH));
	}
	
	private void setNextButtonElement() {
		this.nextButtonElement = driver.findElement(By.xpath(NEXT_BUTTON_XPATH));
	}
	
	private void setNumOfTripDaysElement() {
		this.numOfTripDaysElement = driver.findElement(By.xpath(NUM_OF_TRIP_DAYS_XPATH));
	}
	
	public String getFutureDate(int delayDays){ 
	    DateTimeFormatter format = DateTimeFormatter
	            .ofPattern("ddMMyyyy");
	        LocalDateTime now = LocalDateTime.now();
	        LocalDateTime startDate = now.plusDays(delayDays);
	        logService.printToConsoleAndToLog("INFO: getFutureDate:"+startDate.format(format)+" with delay of:"+delayDays+" days");
	        return  startDate.format(format);
	}
	
	public String parseTipDays(String daysRawData) {
		String[] stringsList = daysRawData.split(" ");
		String tripDays = stringsList[1];
		return tripDays;
	}
	
}
