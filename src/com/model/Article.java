package com.model;

import java.util.Date;

public class Article {
int user_id;
int id;
String title;
String content;
Date pubDate;
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Date getPubDate() {
	return pubDate;
}
public void setPubDate(Date pubDate) {
	this.pubDate = pubDate;
}


}
