package org.sid.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table( name = "request" )
public class Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@NotBlank
	@Size(max = 20)
    private String type;

	@NotBlank
	@Size(max = 50)
	private String reason;
	
	@NotBlank
	@Size(max = 50)
	@Email
    private String requesterEmail;
    
	public Request() {
		super();
	}

	public Request(String type, String reason, String requesterEmail) {
		super();
		this.type = type;
		this.reason = reason;
		this.requesterEmail = requesterEmail;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRequesterEmail() {
		return requesterEmail;
	}

	public void setRequesterEmail(String requesterEmail) {
		this.requesterEmail = requesterEmail;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", type=" + type + ", reason=" + reason + ", requesterEmail=" + requesterEmail
				+ "]";
	}

	 
}
