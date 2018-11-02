package com.AppAnalytics.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class TblDimenAndMetrics  implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean users;
	private String name;
	private boolean newUsers;
	private boolean onedayUsers;
	private boolean sevendayUsers;

	private boolean fourteendayUsers;
private boolean twenty8dayUsers;
private boolean thirtydayUsers;
private TblDimenAndMetrics dimention;
private TblMetrics metrics;

public TblDimenAndMetrics getDimention() {
	return dimention;
}
public void setDimention(TblDimenAndMetrics dimention) {
	this.dimention = dimention;
}
public TblMetrics getMetrics() {
	return metrics;
}
public void setMetrics(TblMetrics metrics) {
	this.metrics = metrics;
}
public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
