package com.sforce.intf.impl;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sforce.soap.enterprise.LoginResult;
import com.sforce.soap.enterprise.LoginScopeHeader;
import com.sforce.soap.enterprise.SessionHeader;
import com.sforce.soap.enterprise.SforceService;
import com.sforce.soap.enterprise.Soap;
import com.sforce.soap.enterprise.UnexpectedErrorFault;

public class SfConnector {
	private static final Logger logger = LoggerFactory.getLogger(SfConnector.class);
	protected Soap soap;
	protected SessionHeader sh;
	protected Boolean connected = Boolean.FALSE;
	protected String account;
	protected String password;
	
	protected String component = "REQ01";
	
	public void connect() {
		SforceService sf = new SforceService();
		soap = sf.getSoap();
		
		LoginScopeHeader lsh = new LoginScopeHeader();
		LoginResult login = null;
		try {
			login = soap.login("fiti02@mxic.com.tw.uat", "t25146875", lsh);
			String surl = login.getServerUrl();
			sh = new SessionHeader();
			sh.setSessionId(login.getSessionId());
			Map<String, Object> requestContext = ((BindingProvider)soap).getRequestContext();
			requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, surl);
			connected = Boolean.TRUE;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			soap.logout(sh);
			connected = Boolean.FALSE;
		} catch (UnexpectedErrorFault e) {
			e.printStackTrace();
		}
	}

	public Soap getSoap() {
		return soap;
	}

	public void setSoap(Soap soap) {
		this.soap = soap;
	}

	public SessionHeader getSh() {
		return sh;
	}

	public void setSh(SessionHeader sh) {
		this.sh = sh;
	}

	public Boolean getConnected() {
		return connected;
	}

	public void setConnected(Boolean connected) {
		this.connected = connected;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}
	
	
}
