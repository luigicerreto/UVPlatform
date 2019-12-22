package model_uvp;
import interfacce.UserInterface;
import model.Attached;

import java.text.SimpleDateFormat;
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
	private String theme;
	private String userFullName;
	private int id_ie;
	private int id_ii;
	private String state;
	private List<Attached> attached = new ArrayList<>();

	public RequestInternship() {

	}

	public RequestInternship(int id_request_i, String state, String type, String theme, String userFullName, int id_ie,
			int id_ii, List<Attached> attached) {
		this.state=state;
		this.attached=attached;
		this.id_ie=id_ie;
		this.id_ii=id_ii;
		this.type=type;
		this.id_request_i=id_request_i;
		this.theme=theme;
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

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Attached> getAttached() {
		return attached;
	}

	public void setAttached(List<Attached> attached) {
		this.attached = attached;
	}

}