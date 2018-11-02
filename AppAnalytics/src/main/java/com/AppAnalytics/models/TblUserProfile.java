package com.AppAnalytics.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@Table(name = "tbl_user_profile", catalog = "appanalytics")
@DynamicUpdate(value = true)
@SelectBeforeUpdate
public class TblUserProfile implements  java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "USER_ID", unique = true, nullable = false)
	private Integer userId;
	
	@Column(name = "F_NAME")
	private String firstName;
	@Column(name = "L_NAME")
	private String lastName;
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "ROLE_ID")
	private Integer roleId;
	@Column(name = "REC_INC_DT")
	private Date recInsDt;
	@Column(name = "REC_LAST_UPD_DT")
	private Date recLastUpdDt;
	@Column(name = "REC_INS_USR_ID")
	private Integer recInsUsrid;
	@Column(name = "REC_LAST_UPD_USR_ID")
	private Integer recLastUpdUsrid;


	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Date getRecInsDt() {
		return recInsDt;
	}
	public void setRecInsDt(Date recInsDt) {
		this.recInsDt = recInsDt;
	}
	public Date getRecLastUpdDt() {
		return recLastUpdDt;
	}
	public void setRecLastUpdDt(Date recLastUpdDt) {
		this.recLastUpdDt = recLastUpdDt;
	}
	public Integer getRecInsUsrid() {
		return recInsUsrid;
	}
	public void setRecInsUsrid(Integer recInsUsrid) {
		this.recInsUsrid = recInsUsrid;
	}
	public Integer getRecLastUpdUsrid() {
		return recLastUpdUsrid;
	}
	public void setRecLastUpdUsrid(Integer recLastUpdUsrid) {
		this.recLastUpdUsrid = recLastUpdUsrid;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	}
