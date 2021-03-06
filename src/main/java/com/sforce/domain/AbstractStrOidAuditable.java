package com.sforce.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AbstractStrOidAuditable extends AbstractStrOIdObject implements Auditable {

	private static final long serialVersionUID = -3490361723232389466L;

	@Column(name = "CREATE_ACCOUNT", length = 50)
	protected String createdAccount;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	protected Date createdDate;

	@Column(name = "MODIFY_ACCOUNT", length = 50)
	protected String modifiedAccount;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_DATE")
	protected Date modifiedDate;
	
	@Override
	public String getCreatedAccount() {
		return this.createdAccount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedAccount() {
		return modifiedAccount;
	}

	public void setModifiedAccount(String modifiedAccount) {
		this.modifiedAccount = modifiedAccount;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setCreatedAccount(String createdAccount) {
		this.createdAccount = createdAccount;
	}
	
}
