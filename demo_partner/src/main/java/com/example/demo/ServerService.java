package com.example.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ServerService {
	static HashMap<Long, Player> playersMapCache = new HashMap<Long, Player>();
	static String playersTxtFile = "players.txt";

	static String getUrlContents(String theUrl)  {  
		StringBuilder content = new StringBuilder();  
		try  
		{  
			URL url = new URL(theUrl);
			URLConnection urlConnection = url.openConnection();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));  
			String line;  
			while ((line = bufferedReader.readLine()) != null)  
			{  
				content.append(line + "\n");  
			}  
			bufferedReader.close();  
		}  
		catch(Exception e)  {  
			e.printStackTrace();  
		}  

		initPlayersMapCache(content.toString());

		return "Cache initialized";  
	}  

	private static void initPlayersMapCache(String data) {
		JSONParser parser = new JSONParser();
		Player player;
		try {
			JSONArray array = (JSONArray) parser.parse(data);
			for(int i=0;i<array.size();i++){
				JSONObject obj =  (JSONObject)array.get(i);
				player = (parseObjectAndSaveToPlayer(obj));
				playersMapCache.put(Long.parseLong(player.getId()), player);
			}

		}

		catch(ParseException e)
		{
			System.out.println(e.getStackTrace()+ " :Could not parse");
		}
	}

	private static Player parseObjectAndSaveToPlayer(JSONObject obj) {

		JSONObject person = (JSONObject)obj.get("person");
		String id = ((person.get("id") == null) || (person.get("id").toString() == null) ? "null" : person.get("id").toString());
		String url = ((person.get("url") == null) || (person.get("url").toString() == null) ? "null" : person.get("url").toString());
		String name = ((person.get("name") == null) || (person.get("name").toString() == null) ? "null" : person.get("name").toString());
		String birthday = ((person.get("birthday") == null) || (person.get("birthday").toString() == null) ? "null" : person.get("birthday").toString());
		String deathday = ((person.get("deathday") == null) || (person.get("deathday").toString() == null) ? "null" : person.get("deathday").toString());
		String gender = ((person.get("gender") == null) || (person.get("gender").toString() == null) ? "null" : person.get("gender").toString());

		System.out.println("Player details: id: "+id+", url: "+url+", name: "+name
				+", birthday: "+birthday+", deathday: "+deathday+", gender: "+gender);

		Player player = new Player(id, url, name, birthday, deathday, gender);
		return player;

	}

	public static HashMap<Long, Player> getPlayersMapCache(){
		return playersMapCache;
	}

	public static String getPlayerById(Long id) {
		try {
			Player player = playersMapCache.get(id);
			return player.toString();
		}
		catch(Exception e) {
			return "Player with id:"+id+" was not found";
		}

	}

	public static void deletePlayerById(Long id) {
		playersMapCache.remove(id);
	}

	public static String addCommentToPlayer(Long id, String comment) {
		try {
			Player player = playersMapCache.get(id);
			player.setComment(comment);		
			createFile(playersTxtFile);
			writeToFile(playersTxtFile, player.toString());
			return "Added comment to player with id:"+id+" and saved to file:"+playersTxtFile;
		}
		catch(Exception e) {
			return "Error during writing comment to player with id:"+id;
		}

	}
	public static void createFile(String fileName) throws IOException {
		File myObj = new File(fileName);
		if (myObj.createNewFile()) {
			System.out.println("File created: " + myObj.getName());
		} else {
			System.out.println("File already exists.");
		}

	}
	
	public static void writeToFile(String fileName, String data) throws IOException {

		FileWriter fw = new FileWriter(fileName, true);
	    BufferedWriter bw = new BufferedWriter(fw);
	    bw.write(data);
	    bw.close();
		System.out.println("Successfully wrote to the file.");
	}
	
	public static Set<Long> getPlayersIdsSet(){
		return playersMapCache.keySet();
	}
	
	public static HashMap<Long, String> getPlayersHashMapString(){
		Map<Long, String> playersStringMap = playersMapCache.entrySet().stream()
		        .collect(Collectors.toMap(
		                e -> e.getKey(),
		                e -> e.getValue().toString()));
		return (HashMap<Long, String>) playersStringMap;
	}


}


