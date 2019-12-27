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
	private int id_request_i;
	private String type;
	private String userEmail;
	private String userFullName;
	private String userSerial;
	private int id_ie;
	private int id_ii;
	private String status;
	private List<Attached> attached = new ArrayList<>();

	public RequestInternship() {

	}

	public RequestInternship(int id_request_i, String status, String type, String userSerial, String userFullName, int id_ie,
			int id_ii, List<Attached> attached) {
		this.status=status;
		this.attached=attached;
		this.id_ie=id_ie;
		this.id_ii=id_ii;
		this.type=type;
		this.id_request_i=id_request_i;
		this.userSerial=userSerial;
		this.userFullName=userFullName;

	}

	public int getId_request_i() {
		return id_request_i;
	}

	public void setId_request_i(int id_request_i) {
		this.id_request_i = id_request_i;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserName() {
		return userFullName.substring(0,userFullName.indexOf("+"));
	}

	public String getUserSurname() {
		return userFullName.substring(userFullName.indexOf("+")+1);
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public int getId_ie() {
		return id_ie;
	}

	public void setId_ie(int id_ie) {
		this.id_ie = id_ie;
	}

	public int getId_ii() {
		return id_ii;
	}

	public void setId_ii(int id_ii) {
		this.id_ii = id_ii;
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(String userSerial) {
		this.userSerial = userSerial;
	}
}