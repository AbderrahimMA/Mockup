package com.otp.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.otp.model.donnees.OtpStatus;

@Entity
@Table(name = "otp")

public class Otp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateGenetation = new Timestamp(System.currentTimeMillis());
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateExpiration;

	OtpStatus status;
	private char[] otpnum;

	public Otp() {
		super();
	}

	public Otp(Date dateGenetation, Date dateExpiration, OtpStatus status, char[] otpValue) {
		super();
		this.dateGenetation = dateGenetation;
		this.dateExpiration = dateExpiration;
		this.status = status;
		this.otpnum = otpValue;
	}

	public Otp(int i) {
		this.id = i;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateGenetation() {
		return dateGenetation;
	}

	public void setDateGenetation(Date dateGenetation) {
		this.dateGenetation = dateGenetation;
	}

	public Date getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public OtpStatus getStatus() {
		return status;
	}

	public void setStatus(OtpStatus status) {
		this.status = status;
	}

	public char[] getOtpNum() {
		return otpnum;
	}

	public void setOtpNum(char[] otpValue) {
		this.otpnum = otpValue;
	}

}
