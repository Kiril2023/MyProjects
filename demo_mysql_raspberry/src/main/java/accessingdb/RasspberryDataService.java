package accessingdb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import accessingdb.RaspberrypiData;
import accessingdb.RaspberrypiDataRepository;

@Service
public class RasspberryDataService {
	@Autowired 
	private RaspberrypiDataRepository raspberrypiDataRepository;
	private final static String LOG_FILE_NAME = "src\\main\\resources\\BE_dev_exam_log.csv";
	public void readLogFileAndSaveToDb() throws FileNotFoundException {

	    try {
	    	boolean firstRun = true;
	        File file = new File(LOG_FILE_NAME);
	        FileReader fr = new FileReader(file);
	        BufferedReader br = new BufferedReader(fr);
	        String line = " ";
	        String[] tempArr;
	        while ((line = br.readLine()) != null) {
	          if(firstRun) {
	        	  firstRun = false;
	        	  continue;
	          }
	          tempArr = line.split(",");
	          saveLogLine(tempArr[0],tempArr[1],tempArr[2],tempArr[3]);
	          for (String tempStr: tempArr) {
	            System.out.print(tempStr + " ");
	          }
	          System.out.println();
	        }
	        br.close();
	      }
	      catch(IOException ioe) {
	        ioe.printStackTrace();
	      }
	  }
	
	private void saveLogLine(String timestamp, String kwh, String pressure, String temperature) {
		RaspberrypiData raspberryData = new RaspberrypiData(timestamp, 
				Integer.parseInt(kwh), Double.parseDouble(pressure), Double.parseDouble(temperature));
		raspberrypiDataRepository.save(raspberryData);
	}
	
	public RaspberrypiData parseJsonRaspberryData(String jsonData) {
		JSONObject jsonObject = new JSONObject(jsonData);

		String timestamp = jsonObject.getString("timestamp");
		String kwh = jsonObject.getString("kwh");
		String pressure = jsonObject.getString("pressure");
		String temperature = jsonObject.getString("temperature");
		RaspberrypiData raspberryData = new RaspberrypiData(timestamp, 
				Integer.parseInt(kwh), Double.parseDouble(pressure), Double.parseDouble(temperature));
		
		System.out.println("timestamp: "+timestamp+"\n");
		System.out.println("kwh: "+kwh+"\n");
		System.out.println("pressure: "+pressure+"\n");
		System.out.println("temperature: "+temperature+"\n");
		return raspberryData;
		
	}
	
	public double getAverageTemperature (String startTime, String endTime) {
		List<RaspberrypiData> betweenDateData= raspberrypiDataRepository.findByDateBetween(startTime, endTime);
		
		Double tempSum=(double) 0;
		for(RaspberrypiData raspberrypiData: betweenDateData) {
			tempSum =+raspberrypiData.getTemperature();
		}
		
		return tempSum/betweenDateData.size();
	}
	
	public int getSumKwh (String startTime, String endTime) {
		List<RaspberrypiData> betweenDateData= raspberrypiDataRepository.findByDateBetween(startTime, endTime);
		
		int sumKwh=0;
		for(RaspberrypiData raspberrypiData: betweenDateData) {
			sumKwh =+raspberrypiData.getKwh();
		}
		
		return sumKwh;
	}
	
	public double getMaxPressure(String startTime, String endTime) {
		List<RaspberrypiData> betweenDateData= raspberrypiDataRepository.findByDateBetween(startTime, endTime);
		
		Double maxPressure=(double) 0;
		for(RaspberrypiData raspberrypiData: betweenDateData) {
			if(raspberrypiData.getPressure() > maxPressure) {
				maxPressure = raspberrypiData.getPressure();
			}
		}
		
		return maxPressure;
	}

	
	public static Long getTimeStampMill (String timestamp) {
		long millisecondsDate = 0L;
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			Date date = simpleFormat.parse(timestamp);
		    millisecondsDate = date.getTime();
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		return millisecondsDate;
	}
	
	public static void main(String args[]) {
//		System.out.println(getAverageTemperature("1/1/2022 13:00", "1/1/2022 13:04"));
	}
	

}
