package model_uvp;

/**
 * 
 * @author carmine
 *
 */
public class Internship {	
	private int id;
	private int availability;
	private String fk_tutor;
	
	public Internship(int id, int availability) {
		this.id = id;
		this.availability = availability;
	}
	
	public Internship() {

	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getAvailability() {
		return availability;
	}
	
	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public String getFk_tutor() {
		return fk_tutor;
	}

	public void setFk_tutor(String fk_tutor) {
		this.fk_tutor = fk_tutor;
	}
}
