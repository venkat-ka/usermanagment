package com.techapp.usermanagement.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="access")
public class Access implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5792735796619944030L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false, length=50)
	private String name;
	
	@Column(nullable = false, length=50)
	private String access;
	
	@OneToMany(mappedBy="acc_id", cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	@Column(columnDefinition="0")
	private Set<Userrole> userRole = new HashSet<>();
	
	
	public Access() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Access(String name, String access) {
		super();
		this.name = name;
		this.access = access;
	}

	public void addUserrole(Userrole usr) {
		userRole.add(usr);
        usr.setAcc_id(this);
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

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}
	
		
	

	public Set<Userrole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<Userrole> userRole) {
		this.userRole = userRole;
	}

	


	
}
