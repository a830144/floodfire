package edu.nccu.floodfire.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length=30)
	@Id private String iduser;

	@Column(length=20)
	private String password;

    public User() {
    }

	public String getIduser() {
		return this.iduser;
	}

	public void setIduser(String iduser) {
		this.iduser = iduser;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}