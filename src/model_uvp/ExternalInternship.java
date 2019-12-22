package model_uvp;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * 
 * @author Andrea Riccelli
 *
 */
public class ExternalInternship {
	private int id_ie;
	private String name;
	private int duration_convention;
	private Date date_convention;
	private int availability;
	private String info;
	private String user;
	private String place;

	public ExternalInternship() {

	}

	public ExternalInternship(int id_ie, String name, int duration_convention, Date date_convention,
			int availability, String info, String user, String place) {
		this.id_ie = id_ie;
		this.name = name;
		this.duration_convention = duration_convention;
		this.date_convention = date_convention;
		this.availability = availability;
		this.info = info;
		this.user = user;
		this.place = place;
	}

	public int getId_ie() {
		return id_ie;
	}

	public void setId_ie(int id_ie) {
		this.id_ie = id_ie;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration_convention() {
		return duration_convention;
	}

	public void setDuration_convention(int duration_convention) {
		this.duration_convention = duration_convention;
	}

	public Date getDate_convention() {
		return date_convention;
	}

	public void setDate_convention(Date date_convention) {
		this.date_convention = date_convention;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

}
