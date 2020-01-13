package model_uvp;

import model.Attached;


import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Andrea Riccelli
 *
 */
public class RequestInternship {
	private int ID_REQUEST_I; // id richiesta
	private int FK_I; // foreign key tirocinio interno/esterno
	private int type;
	private String status;
	private List<Attached> attached = new ArrayList<>();
	private User student;
	private User tutor;
	private Internship internship;

	public RequestInternship() {

	}

	public RequestInternship(int id_request_i, String status, int type, int fk_i, List<Attached> attached) {
		this.status=status;
		this.attached=attached;
		this.FK_I=fk_i;
		this.type=type;
		this.ID_REQUEST_I=id_request_i;
	}

	public int getId_request_i() {
		return ID_REQUEST_I;
	}

	public void setId_request_i(int ID_REQUEST_I) {
		this.ID_REQUEST_I = ID_REQUEST_I;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getFk_i() {
		return FK_I;
	}

	public void setFk_i(int FK_I) {
		this.FK_I=FK_I;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Attached> getAttached() {
		return attached;
	}

	public void setAttached(List<Attached> attached) {
		this.attached = attached;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public User getTutor() {
		return tutor;
	}

	public void setTutor(User tutor) {
		this.tutor = tutor;
	}

	public Internship getInternship() {
		return internship;
	}

	public void setInternship(Internship internship) {
		this.internship = internship;
	}
}