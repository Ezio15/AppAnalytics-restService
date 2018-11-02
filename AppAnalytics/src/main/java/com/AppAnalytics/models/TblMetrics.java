package com.AppAnalytics.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_dim_met", catalog = "law")
public class TblMetrics implements Serializable {
private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DIM_AND_MET_ID")
	private Integer userId;
	
	@Column(name = "USERS")
	private boolean users;
	@Column(name = "NEW_USERS")
	private boolean newUsers;
	@Column(name = "ONEDAY_USERS")
	private boolean onedayUsers;
	@Column(name = "SEVENDAY_USERS")
	private boolean sevendayUsers;
	@Column(name = "FOURTEENDAY_USERS")
	private boolean fourteendayUsers;
	@Column(name = "TWENTY8DAY_USERS")
	private boolean twenty8dayUsers;
	@Column(name = "THIRTYDAY_USERS")
	private boolean thirtydayUsers;

	public boolean isUsers() {
		return users;
	}

	public void setUsers(boolean users) {
		this.users = users;
	}

	

	public boolean isNewUsers() {
		return newUsers;
	}

	public void setNewUsers(boolean newUsers) {
		this.newUsers = newUsers;
	}

	public boolean isOnedayUsers() {
		return onedayUsers;
	}

	public void setOnedayUsers(boolean onedayUsers) {
		this.onedayUsers = onedayUsers;
	}

	public boolean isFourteendayUsers() {
		return fourteendayUsers;
	}

	public void setFourteendayUsers(boolean fourteendayUsers) {
		this.fourteendayUsers = fourteendayUsers;
	}

	public boolean isTwenty8dayUsers() {
		return twenty8dayUsers;
	}

	public void setTwenty8dayUsers(boolean twenty8dayUsers) {
		this.twenty8dayUsers = twenty8dayUsers;
	}

	public boolean isThirtydayUsers() {
		return thirtydayUsers;
	}

	public void setThirtydayUsers(boolean thirtydayUsers) {
		this.thirtydayUsers = thirtydayUsers;
	}

	public boolean isSevendayUsers() {
		return sevendayUsers;
	}

	public void setSevendayUsers(boolean sevendayUsers) {
		this.sevendayUsers = sevendayUsers;
	}
}
