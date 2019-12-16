package model_uvp;

public class Teacher {
	  private String email;
	  private String name;
	  private String surname;
	  private char sex;
	  private String password;
	  private int userType;
	  private String ufficio;
	  private int telefono;
	  private int id_teacher;
	  
	  
	public Teacher(String email, String name, String surname, char sex, String password, int userType, String ufficio,
			int telefono,int id_teacher) {
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.password = password;
		this.userType = userType;
		this.ufficio = ufficio;
		this.telefono = telefono;
		this.id_teacher=id_teacher;
	}


	public int getId_teacher() {
		return id_teacher;
	}


	public void setId_docente(int id_teacher) {
		this.id_teacher = id_teacher;
	}


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


	public String getUfficio() {
		return ufficio;
	}


	public void setUfficio(String ufficio) {
		this.ufficio = ufficio;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	  
}

