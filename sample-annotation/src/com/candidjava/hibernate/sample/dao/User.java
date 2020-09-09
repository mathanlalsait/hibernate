package com.candidjava.hibernate.sample.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity    
@Table(name = "std")
public class User 
{	
	@Id   // primary key
	@Column(name = "sid")
	@GeneratedValue(strategy=GenerationType.AUTO)   // auto increment 
	private long id;
	
	@Column(name = "uname")
	private String username;
	
	private String password;

	@Transient
	private int age;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
}
