package com.sforce.to;

import com.sforce.domain.Role;

public class User {
	private String account;
	private Role role;
	
	public User() {
		super();
	}
	
	public User(String account, Role role) {
		super();
		this.account = account;
		this.role = role;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
