package model_uvp;

public class Company{
	
	private String email;
	private int userType;
	private int telefono;
	private String sede;
	private String nome;
	private int id_company;
	
	
	public Company(String email, int userType, int telefono, String sede, String nome, int id_company) {
		super();
		this.email = email;
		this.userType = userType;
		this.telefono = telefono;
		this.sede = sede;
		this.nome = nome;
		this.id_company = id_company;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getUserType() {
		return userType;
	}


	public void setUserType(int userType) {
		this.userType = userType;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}


	public String getSede() {
		return sede;
	}


	public void setSede(String sede) {
		this.sede = sede;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getId_company() {
		return id_company;
	}


	public void setId_company(int id_company) {
		this.id_company = id_company;
	}
	
	
	
	
	
	
	
	
}