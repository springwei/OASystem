package bean;

public class Notice {
	private int id;
	
private String issuer;
private String content;
private String time;
private String title;

public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getIssuer() {
	return issuer;
}
public void setIssuer(String issuer) {
	this.issuer = issuer;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}

}
