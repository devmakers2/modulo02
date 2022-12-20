package main.java;

public class Post {
	private final String date;
	private final String hour;
	private final String content;
	
	Post(String date, String hour, String content) {
		this.date = date;
		this.hour = hour;
		this.content = content;
	}

	String getDate() {
		return this.date;
	}

	String getHour() {
		return this.hour;
	}

	String getContent() {
		return this.content;
	}
}
