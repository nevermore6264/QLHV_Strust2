package com.poly.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Account")
public class Account implements java.io.Serializable {

	private int accId;
	private String username;
	private String password;
	private boolean roles;
	private String name;
	private Date birthday;
	private Boolean gender;
	private boolean accStatus;

	public Account() {
	}

	public Account(int accId, String username, String password, boolean roles, boolean accStatus) {
		this.accId = accId;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.accStatus = accStatus;
	}

	public Account(int accId, String username, String password, boolean roles, String name, Date birthday,
                   Boolean gender, boolean accStatus) {
		this.accId = accId;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.accStatus = accStatus;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "AccId", unique = true, nullable = false)
	public int getAccId() {
		return this.accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	@Column(name = "Username", nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "Password", nullable = false, length = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "Roles", nullable = false)
	public boolean isRoles() {
		return this.roles;
	}

	public void setRoles(boolean roles) {
		this.roles = roles;
	}

	@Column(name = "Name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Birthday", length = 23)
	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "Gender")
	public Boolean getGender() {
		return this.gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	@Column(name = "AccStatus", nullable = false)
	public boolean isAccStatus() {
		return this.accStatus;
	}

	public void setAccStatus(boolean accStatus) {
		this.accStatus = accStatus;
	}

	private int genders;
	public String getGenders(){
		Boolean boolean1 = Boolean.valueOf("true");
		if(this.gender==boolean1){
			return "Nam";
		}else{
			return "Nữ";
		}
	}
	
	private int status;
	public String getStatus(){
		Boolean boolean1 = Boolean.valueOf("true");
		if(this.accStatus==boolean1){
			return "Mở";
		}else{
			return "Đóng";
		}
	}
	
	private int role;
	public String getRole(){
		Boolean boolean1 = Boolean.valueOf("true");
		if(this.roles==boolean1){
			return "Admin";
		}else{
			return "Nhân viên";
		}
	}
}
