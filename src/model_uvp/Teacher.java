package model_uvp;

import interfacce.UserInterface;
/**
 * 
 * @author Michele Pirro
 *
 */
public class Teacher implements UserInterface{
	  private String email;
	  private String name;
	  private String surname;
	  private char sex;
	  private String password;
	  private int userType;
	  private String serial;
	  private String office;
	  private int phone;
	  
	  
	public Teacher(String email, String name, String surname, char sex, String password, int userType) {
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.password = password;
		this.userType = userType;
		this.serial= serial;
		this.office= office;
		this.phone= phone;
	}
	
	public Teacher() {}

	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public char getSex() {
		return sex;
	}


	public void setSex(char sex) {
		this.sex = sex;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getUserType() {
		return userType;
	}


	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	public String getSerial() {
		return serial;
	}
	
	public void setSerial(String serial) {
		 this.serial= serial;
	}


	public String getOffice() {
		return office;
	}


	public void setOffice(String office) {
		this.office = office;
	}


	public int getPhone() {
		return phone;
	}


	public void setPhone(int phone) {
		this.phone = phone;
	}


	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	  
}

