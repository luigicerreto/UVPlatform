package model_uvp;

import interfacce.UserInterface;
import model.Stub;
/**
 * 
 * @author Andrea Riccelli
 *
 */
public class User implements UserInterface {
	private String email;
	private String name;
	private String surname;
	private char sex;
	private String password;
	private int userType;
	private String serial;
	private String phone;


	public User() {
	}

	public User(String email, String name, String surname, char sex,String password, int userType,
			String serial, String phone) {

		this.email=email;
		this.name=name;
		this.surname=surname;
		this.sex=sex;
		this.password=password;
		this.userType=userType;
		this.serial=serial;
		this.phone=phone;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String string) {
		this.phone = string;
	}

	@Override
	public String getEmail() {
		return email;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getSurname() {
		return surname;
	}
	@Override
	public char getSex() {
		return sex;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public int getUserType() {
		return userType;
	}
	@Override
	public void setEmail(String e) {
		this.email=e;

	}
	@Override
	public void setName(String n) {
		this.name=n;		
	}
	@Override
	public void setSurname(String s) {
		this.surname=s;
	}
	@Override
	public void setSex(char s) {
		this.sex=s;
	}
	@Override
	public void setPassword(String p) {
		this.password=p;
	}
	@Override
	public void setUserType(int u) {
		this.userType=u;
	}
	@Override
	public boolean validate() {
		return new Stub().database.containsKey(getEmail()) 
				&& new Stub().database.containsValue(getPassword());
	}
}
