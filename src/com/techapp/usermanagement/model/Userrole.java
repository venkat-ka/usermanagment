package com.techapp.usermanagement.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="userrole")
public class Userrole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1198221415001191372L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "MEDIUMINT NOT NULL AUTO_INCREMENT")
	private long id;
	
	@Column(nullable = false, length=50)
	private String name;
	
	@Column(nullable = false, length=155, unique = true)	
	private String email;
	
	@Column(nullable = false, length=50)
	private String designation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="acc_id", referencedColumnName="id",nullable = true,  columnDefinition = "bigint default 0")
	private Access acc_id;
	
	public Userrole() {
		
		// TODO Auto-generated constructor stub
	}
	
	

	public Userrole(long id, String name, String email, String designation) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.designation = designation;
	}



	public Userrole(String name, String email, String designation) {
		super();
		this.name = name;
		
		this.email=email;
		this.designation=designation;
	}

	





	public Access getAcc_id() {
		return acc_id;
	}



	public void setAcc_id(Access acc_id) {
		this.acc_id = acc_id;
	}



	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getDesignation() {
		return designation;
	}



	public void setDesignation(String designation) {
		this.designation = designation;
	}













	



	



	




	
}
