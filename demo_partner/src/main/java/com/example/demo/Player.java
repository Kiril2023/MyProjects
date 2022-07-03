package com.example.demo;


public class Player {
	private String id;
	private String url;
	private String name;
	private String birthday;
	private String deathday;
	private String gender;
	private String comment = "";

	public Player (String id, String url, String name, String birthday, String deathday, String gender){
		this.id = id;
		this.url = url;
		this.name = name;
		this.birthday = birthday;
		this.deathday = deathday;
		this.gender = gender;
	}
	public void setId(String id) {
		this.id = id;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setDeathday(String deathday) {
		this.deathday = deathday;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public String getName() {
		return name;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getDeathday() {
		return deathday;
	}

	public String getGender() {
		return gender;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}
	
	public String toString() {
		return "ID:"+id+", URL:"+url+", Name:"+name+", Birthday:"+birthday+", Deathday:"+deathday+
				", Gender:"+gender+" Comment:"+comment;
	}


}