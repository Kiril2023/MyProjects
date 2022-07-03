package services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigValues {


	private final String PROP_FILE_NAME = "config.properties";
	private InputStream inputStream;
	public final static String pageLoginUrlPr = "page_login_url";
	public final static  String userPr = "user";
	public final static  String passwordPr = "password";
	public final static  String logFilePr = "log_file";
	
	public Properties getPropValues() throws IOException {
		Properties prop = new Properties();
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(PROP_FILE_NAME);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + PROP_FILE_NAME + "' not found in the classpath");
			}
 
			String page_login_url = prop.getProperty(pageLoginUrlPr);
			String user = prop.getProperty(userPr);
			String password = prop.getProperty(passwordPr);
			String logFile = prop.getProperty(logFilePr);
			String result = "page_login_url:" + page_login_url + " user:" + user + " password:" + password+
					" logFile:"+logFile;
			System.out.println(result);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return prop;
	}

}
