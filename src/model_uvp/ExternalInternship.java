package model_uvp;

import java.sql.Date;

/**
 * 
 * @author Andrea Riccelli
 *
 */
public class ExternalInternship extends Internship {
	private String name;
	private int duration_convention;
	private Date date_convention;
	private String info;


	public ExternalInternship(int id_ie, String name, int duration_convention, Date date_convention,
			int availability, String info) {
		super(id_ie, availability);
		this.name = name;
		this.duration_convention = duration_convention;
		this.date_convention = date_convention;
		this.info = info;
	} 
	
	public ExternalInternship() {
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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
