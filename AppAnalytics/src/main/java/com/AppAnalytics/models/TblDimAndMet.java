package com.AppAnalytics.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_dim_met_fields", catalog = "law")
public class TblDimAndMet implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "DIMENSIONS")
	private String dimensions;

	@Column(name = "METRICS")
	private String metrics;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public String getMetrics() {
		return metrics;
	}

	public void setMetrics(String metrics) {
		this.metrics = metrics;
	}
	
/*	private int dimAndMetId;
	@Column(name = "USER_TYPE")
	private boolean  userType;
	@Column(name = "USERS")
	private boolean users;
	
	public boolean isUserType() {
		return userType;
	}
	public void setUserType(boolean userType) {
		this.userType = userType;
	}
	public boolean isUsers() {
		return users;
	}
	public void setUsers(boolean users) {
		this.users = users;
	}
*/}
