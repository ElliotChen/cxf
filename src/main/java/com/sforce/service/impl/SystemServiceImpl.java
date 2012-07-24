package com.sforce.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sforce.domain.Role;
import com.sforce.service.SystemService;
import com.sforce.to.User;
import com.sforce.util.ThreadLocalHolder;

@Component("systemService")
public class SystemServiceImpl implements SystemService {
	@Value("${system.password}")
	private String password;
	@Override
	public boolean logon(String password) {
		if (!password.equals(this.password)) {
			return false;
		}
		
		ThreadLocalHolder.setUser(new User("System", Role.Admin));
		return true;
	}

}
