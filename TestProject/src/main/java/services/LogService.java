package services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogService {

	
	private String fileName;
	private final static String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
	public LogService(String fileName) throws IOException {
		this.fileName = fileName;
		createFile();
	}

	private void createFile() throws IOException {
		File myObj = new File(fileName);
		Files.deleteIfExists(myObj.toPath());
		if (myObj.createNewFile()) {
			System.out.println("INFO: Log File was created: " + fileName);
		} else {
			System.out.println("INFO: Log File "+fileName+" already exists.");
		}

	}

	private void writeToFile(String data) throws IOException {
		FileWriter fw = new FileWriter(fileName, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(data);
		bw.close();
	}
	
	public void printToConsoleAndToLog(String data) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
		String date = simpleDateFormat.format(new Date());

		System.out.println(date+" "+data);
		try {
			writeToFile(date+" "+data+'\n');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}

}
