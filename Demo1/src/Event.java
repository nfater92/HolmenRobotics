
public class Event{
	protected String event;
	protected String username;
	protected String date;
	protected String time;
	protected String location;
	protected String description;
	protected String details;
	protected int usertype;
	protected int status;// 0 for pending, 1 for accepted, -1 for declined
	protected int id;
	protected String timeStamp;
	
	public Event(){
		
	}
	public String getRequest() {
		return event;
	}
	public void setRequest(String request) {
		this.event = request;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getUsertype() {
		return usertype;
	}
	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
}
