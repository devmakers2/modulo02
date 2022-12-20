package main.java;

public class Post {
	private final String date;
	private final String hour;
	private final String content;
	private final String author;
	
	Post(String date, String hour, String content, String author) {
		this.date = date;
		this.hour = hour;
		this.content = content;
		this.author = author;
	}

	protected String getAuthor() {
		return this.author;
	}

	protected String getFormattedPost() {
		return this.date + " às " + this.hour + " - \"" + this.content + "\"";
	}
}
