package com.example.demo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {
	
    @GetMapping("/players")
    public String getPlayersData(Model model)  {
    	  HashMap<Long, String> playersIdsSet = ServerService.getPlayersHashMapString();
    	  model.addAttribute("playerMap", playersIdsSet);
    	  return "welcome";
 
    }
    
    
    @Scheduled(fixedRateString = "${cache.refresh.milliseconds}")
    @GetMapping("/initPlayers")
    @ResponseBody
    public String initPlayersData()  {
    	  ServerService.getUrlContents("https://api.tvmaze.com/shows/1/cast");
    	  System.out.println("cache initialized");
    	  return "players cache initialized";
 
    }
       
    @GetMapping("/getPlayer/{id}")
    @ResponseBody
    public String getPlayerById(@PathVariable Long id) {
        String playerString = ServerService.getPlayerById(id).toString();
  	  	return playerString;
    }
    
    @RequestMapping(value = "/getPlayer", method = RequestMethod.POST)
    @ResponseBody
    public String getPlayerByPost(Long id) {
        String playerString = ServerService.getPlayerById(id).toString();
  	  	return playerString;
    }
    
    @GetMapping("/deletePlayer/{id}")
    @ResponseBody
    public String deletePlayerById(@PathVariable Long id) {
        ServerService.deletePlayerById(id);
        return "Player with ID:"+id+" deleted";
    }
    
    @RequestMapping(value = "/deletePlayer", method = RequestMethod.POST)
    @ResponseBody
    public String deletePlayerByIdPost(Long id) {
        ServerService.deletePlayerById(id);
        return "Player with ID:"+id+" deleted";
    }
    
    @GetMapping("/addCommentToPlayer/{id}/{comment}")
    @ResponseBody
    public String addCommentPlayerById(@PathVariable Long id, @PathVariable String comment) {
       return ServerService.addCommentToPlayer(id, comment);
    }
    
    @RequestMapping(value = "/addCommentToPlayer", method = RequestMethod.POST)
    @ResponseBody
    public String addCommentPlayerByIdPost(String data) {
    	String[] objData = data.split(":");
    	Long id = Long.parseLong(objData[0]);
    	String comment = objData[1];
       return ServerService.addCommentToPlayer(id, comment);
    }

}
