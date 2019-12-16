package model_uvp;

public class InternalInternship {
   private int id_ii;
   private String tutorn_name;
   private String theme;
   private int availability;
   private String resources;
   private String goals;
   private String place;
   
   
   

   
public InternalInternship(int id_ii, String tutorn_name, String theme, int availability, String resources, String goals,
		String place) {
	super();
	this.id_ii = id_ii;
	this.tutorn_name = tutorn_name;
	this.theme = theme;
	this.availability = availability;
	this.resources = resources;
	this.goals = goals;
	this.place = place;
}
public int getId_ii() {
	return id_ii;
}
public void setId_ii(int id_ii) {
	this.id_ii = id_ii;
}
public String getTutorn_name() {
	return tutorn_name;
}
public void setTutorn_name(String tutorn_name) {
	this.tutorn_name = tutorn_name;
}
public String getTheme() {
	return theme;
}
public void setTheme(String theme) {
	this.theme = theme;
}
public int getAvailability() {
	return availability;
}
public void setAvailability(int availability) {
	this.availability = availability;
}
public String getResources() {
	return resources;
}
public void setResources(String resources) {
	this.resources = resources;
}
public String getGoals() {
	return goals;
}
public void setGoals(String goals) {
	this.goals = goals;
}
public String getPlace() {
	return place;
}
public void setPlace(String place) {
	this.place = place;
}
}
   
   
