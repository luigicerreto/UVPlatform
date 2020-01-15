package model_uvp;

/**
 * 
 * @author Andrea Riccelli
 *
 */
public class InternalInternship extends Internship{
	private String tutor_name;
	private String theme;
	private String resources;
	private String goals;
	private String place;

	public InternalInternship(int id_ii, String tutorn_name, String theme, int availability, String resources, String goals,
			String place) {
		super(id_ii, availability);
		this.tutor_name = tutorn_name;
		this.theme = theme;
		this.resources = resources;
		this.goals = goals;
		this.place = place;
	}
	
	public InternalInternship() {
		super();
	}

	public String getTutor_name() {
		return tutor_name;
	}
	
	public void setTutor_name(String tutor_name) {
		this.tutor_name = tutor_name;
	}
	
	public String getTheme() {
		return theme;
	}
	
	public void setTheme(String theme) {
		this.theme = theme;
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


