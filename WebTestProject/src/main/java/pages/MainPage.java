package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import services.LogService;
import services.TestService;

public class MainPage {

	private WebDriver driver;
	private LogService logService;
	public static final String URL = "https://practis.co.il/automation/main.php";
	private final String COLOR_RGB_255_127_80 = "rgb(255, 127, 80)";
	private final String COLOR_HEX = "#FF7F50";
	private final String DO1_BUTTON_ELEMENT_ID = "do1";
	private final String DO2_BUTTON_ELEMENT_ID = "do2";
	private final String FONT_SIZE_P_ELEMENT_ID = "textFontSize";
	private final String INCREASE_BUTTON_ELEMENT_ID = "btnIncreaseFont";
	private final String DECREASE_BUTTON_ELEMENT_ID = "btnDecreaseFont";
	private final String COLOR_FORM_ELEMENT_ID = "formToColorize";
	private final String COLOR_INPUT_ELEMENT_ID = "bgColor";
	private final String SET_COLOR_BUTTON_ELEMENT_ID = "btnSetBgColor";
	private WebElement do1ButtonElement;
	private WebElement do2ButtonElement;
	private WebElement fontSizeParElement;
	private WebElement increaseButtonElement;
	private WebElement decreaseButtonElement;
	private WebElement colorFormElement;
	private WebElement colorInputElement;
	private WebElement colorButtonElement;

	public MainPage(WebDriver driver, LogService logService) {
		this.driver = driver;
		this.logService = logService;
		
	}

	private void setDo1ButtonElementById() {
		this.do1ButtonElement = driver.findElement(By.id(DO1_BUTTON_ELEMENT_ID));
	}

	private void setDo2ButtonElementById() {
		this.do2ButtonElement = driver.findElement(By.id(DO2_BUTTON_ELEMENT_ID));
	}

	private void setFontSizeParElementById() {
		this.fontSizeParElement = driver.findElement(By.id(FONT_SIZE_P_ELEMENT_ID));
	}
	
	private void setIncreaseButtonElementById() {
		this.increaseButtonElement = driver.findElement(By.id(INCREASE_BUTTON_ELEMENT_ID));
	}

	private void setDecreaseButtonElementById() {
		this.decreaseButtonElement = driver.findElement(By.id(DECREASE_BUTTON_ELEMENT_ID));
	}

	private void setColorFormElementById() {
		this.colorFormElement = driver.findElement(By.id(COLOR_FORM_ELEMENT_ID));
	}
	
	private void setColorInputElementById() {
		this.colorInputElement = driver.findElement(By.id(COLOR_INPUT_ELEMENT_ID));
	}
	
	private void setColorButtonElementById() {
		this.colorButtonElement = driver.findElement(By.id(SET_COLOR_BUTTON_ELEMENT_ID));
	}
		
	public boolean do1Do2ButtonsTest() {
		logService.printToConsoleAndToLog("INFO: Starting test of mainPage.do1Do2ButtonsTest");
		setDo1ButtonElementById();
		setDo2ButtonElementById();
		if(do1ButtonElement.isEnabled()&&do2ButtonElement.isEnabled()) {
			logService.printToConsoleAndToLog("ERROR: DO1! and DO2! buttons both are enabled");
		}
		
		else if(!do1ButtonElement.isEnabled()&&!do2ButtonElement.isEnabled()) {
			logService.printToConsoleAndToLog("ERROR: DO1! and DO2! buttons both are disabled");
			return false;
		}
		
		do1ButtonElement.click();
		if(!do1ButtonElement.isEnabled()&&do2ButtonElement.isEnabled()) {
			logService.printToConsoleAndToLog("SUCCESS: Clicking on DO1! changed it to disabled and DO2! to enabled");
		}
		else {
			logService.printToConsoleAndToLog("ERROR: Clicking on DO1! didnt change it to disabled and DO2! to enabled");
			return false;
		}
		
		do2ButtonElement.click();
		if(do1ButtonElement.isEnabled()&&!do2ButtonElement.isEnabled()) {
			logService.printToConsoleAndToLog("SUCCESS: Clicking on DO2! changed it to disabled and DO1! to enabled");
			return true;
		}
		else {
			logService.printToConsoleAndToLog("ERROR: Clicking on DO2! didnt change it to disabled and DO1! to enabled");
			return false;
		}
		
	}
	
	public boolean fontSizeChangeTest() {
		logService.printToConsoleAndToLog("INFO: Starting test of mainPage.fontSizeChangeTest");
		boolean result = true;
		setFontSizeParElementById();
		setIncreaseButtonElementById();
		setDecreaseButtonElementById();
		String defaultParFontSize = fontSizeParElement.getCssValue("font-size");
		logService.printToConsoleAndToLog("INFO: Default font size of paragraph element is:"+defaultParFontSize);
		
		increaseButtonElement.click();
		String changedParFontSize = fontSizeParElement.getCssValue("font-size");
		if(TestService.compareFontSizePx(changedParFontSize, defaultParFontSize) == 1) {
			logService.printToConsoleAndToLog("SUCCESS: Font size increased to:"+ changedParFontSize);
		}
		else {
			logService.printToConsoleAndToLog("ERROR: Font size was not increased, the size now is:"+ changedParFontSize);
			result = false;
		}
		
		decreaseButtonElement.click();
		decreaseButtonElement.click();
		changedParFontSize = fontSizeParElement.getCssValue("font-size");
		if(TestService.compareFontSizePx(changedParFontSize, defaultParFontSize) == 2) {
			logService.printToConsoleAndToLog("SUCCESS: Font size decreased to:"+ changedParFontSize);
		}
		else {
			logService.printToConsoleAndToLog("ERROR: Font size was not decreased, the size now is:"+ changedParFontSize);
			result = false;
		}
		
		return result;
		
	}
	
	public boolean backGroundColorChangeTest() {
		logService.printToConsoleAndToLog("INFO: Starting test of mainPage.backGroundColorChangeTest");
		boolean result = true;
		setColorFormElementById();
		setColorInputElementById();
		setColorButtonElementById();
		String defaultRgbColor = TestService.getRgbColorFromString(colorFormElement.getAttribute("style"));

		colorInputElement.sendKeys(COLOR_HEX);
		colorButtonElement.click();
		String changedRgbColor = TestService.getRgbColorFromString(colorFormElement.getAttribute("style"));
		
		if(!changedRgbColor.equals(defaultRgbColor) && changedRgbColor.equals(COLOR_RGB_255_127_80)) {
			logService.printToConsoleAndToLog("SUCCESS: Frame background color was changed to:"+ changedRgbColor);
		}
		else {
			logService.printToConsoleAndToLog("ERROR: Frame background color was not changed to:"+ COLOR_RGB_255_127_80+" the color now is:"+changedRgbColor);
			result = false;
		}
		
		return result;
		
	}
	
	public static String getUrl() {
		return URL;
	}
	
	
	
	
	
	

}