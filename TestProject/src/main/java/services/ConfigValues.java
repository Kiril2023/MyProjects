package services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigValues {

	private final String PROP_FILE_NAME = "config.properties";
	private InputStream inputStream;
	public final static String pageLoginUrlPr = "page_login_url";
	public final static  String logFilePr = "log_file";
	
	public Properties getPropValues() throws IOException {
		Properties prop = new Properties();
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(PROP_FILE_NAME);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + PROP_FILE_NAME + "' was not found in the classpath");
			}

			String logFile = prop.getProperty(logFilePr);
			String result = "logFile:"+logFile;
			System.out.println(result);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return prop;
	}
}
