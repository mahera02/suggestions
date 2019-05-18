package com.sug.iv.suggestions.engine;

public class Location {
	
	String name;
	float latitude;
	float longitude;
	float score;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Location(String name, float latitude, float longitude, float score) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.score = score;
	}
	
	@Override
	public String toString() {
		return "Location [name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + ", score=" + score
				+ "]";
	}
}
