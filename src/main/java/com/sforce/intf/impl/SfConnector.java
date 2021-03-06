package com.sforce.intf.impl;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.sforce.soap.enterprise.GetServerTimestampResult;
import com.sforce.soap.enterprise.LoginResult;
import com.sforce.soap.enterprise.LoginScopeHeader;
import com.sforce.soap.enterprise.SessionHeader;
import com.sforce.soap.enterprise.SforceService;
import com.sforce.soap.enterprise.Soap;
import com.sforce.soap.enterprise.UnexpectedErrorFault;

public class SfConnector {
	private static final Logger logger = LoggerFactory.getLogger(SfConnector.class);
	public static final String DEFAULT_ENCODING = "UTF-8";
	protected static Soap SOAP = null;
	protected static SessionHeader SH = null;
	protected Soap soap;
	protected SessionHeader sh;
	protected Boolean connected = Boolean.FALSE;
	@Value("${sf.account}")
	protected String account;
	@Value("${sf.password}")
	protected String password;
	
	protected String component;
	protected Boolean debugMode = Boolean.FALSE;
	
	protected String[] receivers;
	
	protected String encoding = DEFAULT_ENCODING;
	public void connect(String account, String password) {
		if (null == SOAP) {
			if (createSoap(account, password)) {
				this.soap = SOAP;
			}
		} else {
			if(checkSoap()) {
				this.soap = SOAP;
			} else {
				SOAP = null;
				if (createSoap(account, password)) {
					this.soap = SOAP;
				}
			}
		}
		
		if (null != this.soap) {
			sh = SH;
			connected = Boolean.TRUE;
		}
		/*
		SforceService sf = new SforceService();
		soap = sf.getSoap();
		
		LoginScopeHeader lsh = new LoginScopeHeader();
		LoginResult login = null;
		try {
			login = soap.login("fiti02@mxic.com.tw.uat", "t25146875", lsh);
			String surl = login.getServerUrl();
			sh = new SessionHeader();
			sh.setSessionId(login.getSessionId());
			logger.info("Soap[{}] login and get sessionId[{}], url[{}]", new Object[] {soap, login.getSessionId(), login.getServerUrl()});
			Map<String, Object> requestContext = ((BindingProvider)soap).getRequestContext();
			requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, surl);
			connected = Boolean.TRUE;
		} catch (Exception e) {
			logger.error("[{}] Connect to SalesForce failed", this.component);
			logger.error("message:", e);
			connected = Boolean.FALSE;
		}
		*/
	}
	
	public void disconnect() {
		/*
		try {
			soap.logout(sh);
		} catch (UnexpectedErrorFault e) {
			logger.error("[{}] Disconnect from SalesForce failed", this.component);
			logger.error("message:", e);
		}
		*/
		this.soap = null;
		connected = Boolean.FALSE;
	}
	
	public synchronized static boolean createSoap(String account, String password) {
		if (null != SOAP) {
			return true;
		}
		try {
			SforceService sf = new SforceService();
			SOAP = sf.getSoap();
			LoginScopeHeader lsh = new LoginScopeHeader();
			LoginResult login = SOAP.login(account, password, lsh);
			String surl = login.getServerUrl();
			SH = new SessionHeader();
			SH.setSessionId(login.getSessionId());
			logger.info("Soap[{}] login and get sessionId[{}], url[{}]", new Object[] {SOAP, login.getSessionId(), login.getServerUrl()});
			Map<String, Object> requestContext = ((BindingProvider)SOAP).getRequestContext();
			requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, surl);
			return true;
		} catch (Exception e) {
			SOAP = null;
			logger.error("[{}] Connect to SalesForce failed");
			logger.error("message:", e);
			return false;
		}
	}

	public synchronized boolean checkSoap() {
		try {
			GetServerTimestampResult st = SOAP.getServerTimestamp(SH);
			return true;
		} catch (UnexpectedErrorFault e) {
			logger.error("Check session available failed. Can't get ServerTimestamp.", e);
			return false;
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

	public Boolean getDebugMode() {
		return debugMode;
	}

	public void setDebugMode(Boolean debugMode) {
		this.debugMode = debugMode;
	}

	public String[] getReceivers() {
		return receivers;
	}

	public void setReceivers(String[] receivers) {
		this.receivers = receivers;
	}
	
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}
