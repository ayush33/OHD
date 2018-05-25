package bean;

import java.util.Date;

public class Tokens {
	int id;
	int user_id;
	Date date;
	int facilitator_id;
	String description;
	int facility_id;
	String room_id;
	String status;
	
	public Tokens() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Tokens [id=" + id + ", user_id=" + user_id + ", date=" + date + ", facilitator_id=" + facilitator_id
				+ ", description=" + description + ", facility_id=" + facility_id + ", room_id=" + room_id + ", status="
				+ status + "]";
	}
	public Tokens(int id, int user_id, Date date, int facilitator_id, String description, int facility_id, String room_id,
			String status) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.date = date;
		this.facilitator_id = facilitator_id;
		this.description = description;
		this.facility_id = facility_id;
		this.room_id = room_id;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getFacilitator_id() {
		return facilitator_id;
	}
	public void setFacilitator_id(int facilitator_id) {
		this.facilitator_id = facilitator_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getFacility_id() {
		return facility_id;
	}
	public void setFacility_id(int facility_id) {
		this.facility_id = facility_id;
	}
	public String getRoom_id() {
		return room_id;
	}
	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
