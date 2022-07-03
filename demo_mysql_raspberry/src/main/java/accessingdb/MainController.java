package accessingdb;

import java.io.FileNotFoundException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller 
@RequestMapping(path="/demo") 
public class MainController {
@Autowired 
private RaspberrypiDataRepository raspberrypiDataRepository;
@Autowired
private RasspberryDataService rasspberryDataService;

@PostMapping(path="/add")
public @ResponseBody String addData (@RequestParam String timestamp
   , @RequestParam Integer kwh, @RequestParam Double pressure, @RequestParam Double temperature) {
RaspberrypiData raspberrypiData = new RaspberrypiData(timestamp, kwh, pressure, temperature);
raspberrypiDataRepository.save(raspberrypiData);
 return "Saved";
}

@PostMapping(path="/addJson")
public @ResponseBody String addDataJson(@RequestParam String jsonData) {
	
	RaspberrypiData raspberrypiData = rasspberryDataService.parseJsonRaspberryData(jsonData);
	raspberrypiDataRepository.save(raspberrypiData);
	 return "Saved";
}

@GetMapping(path="/all")
public @ResponseBody Iterable<RaspberrypiData> getAllData() {
 return raspberrypiDataRepository.findAll();
}

//http://127.0.0.1:8080/demo/averageTemp?dates=1/1/2022 13:00,1/1/2022 13:04
@GetMapping(path="/averageTemp")
public @ResponseBody double getAverageTemp(@RequestParam String[] dates) {
 return rasspberryDataService.getAverageTemperature(dates[0], dates[1]);
}

//http://127.0.0.1:8080/demo/sumKwh?dates=1/1/2022 13:00,1/1/2022 13:04
@GetMapping(path="/sumKwh")
public @ResponseBody int getSumKwh(@RequestParam String[] dates) {
return rasspberryDataService.getSumKwh(dates[0], dates[1]);
}

//http://127.0.0.1:8080/demo/maxPressure?dates=1/1/2022 13:00,1/1/2022 13:04
@GetMapping(path="/maxPressure")
public @ResponseBody double getMaxPressure(@RequestParam String[] dates) {
return rasspberryDataService.getMaxPressure(dates[0], dates[1]);
}


@PostConstruct
public void readLogAndSaveToDb() {
	System.out.println("readLogAndSaveToDb");
	try {
		rasspberryDataService.readLogFileAndSaveToDb();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
}
