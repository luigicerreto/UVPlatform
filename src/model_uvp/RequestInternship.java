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
	String user1;
	String user2;
	private int id_ie;
	private int id_ii;
	private String state;
	private List<Attached> attached = new ArrayList<>();

	public RequestInternship() {

	}

	public RequestInternship(int id_request_i, String state, String type, String user1, String user2, int id_ie,
			int id_ii, List<Attached> attached) {
		this.state=state;
		this.attached=attached;
		this.id_ie=id_ie;
		this.id_ii=id_ii;
		this.type=type;
		this.id_request_i=id_request_i;
		this.user1=user1;
		this.user2=user2;

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

	public String getUser1() {
		return user1;
	}

	public void setUser1(String user1) {
		this.user1 = user1;
	}

	public String getUser2() {
		return user2;
	}

	public void setUser2(String user2) {
		this.user2 = user2;
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