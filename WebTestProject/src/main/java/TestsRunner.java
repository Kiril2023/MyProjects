import java.util.Properties;

import org.openqa.selenium.WebDriver;

import services.ConfigValues;
import services.LogService;
import services.WebDriverService;
import tests.Test1;

public class TestsRunner {

	public static void main(String[] args) {
		try {
			ConfigValues properties = new ConfigValues();
			Properties props = properties.getPropValues();
			LogService logService = new LogService(props.getProperty(ConfigValues.logFilePr));
			WebDriverService driverService = new WebDriverService(logService);
			driverService.initChromDriver();
			WebDriver driver = driverService.getChromeDriver();			
			Test1 test1= new Test1(props, driver, logService);
			test1.runTests();
			driver.quit();
					
		} catch (Exception e) {
			System.out.println("ERROR: Failed to start tests:"+e.toString());
		}
		
	}
}
