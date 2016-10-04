package com.code.generate.DTO;

import java.io.Serializable;

public class ProcessDTO extends StausDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String userId;
	
	private String secure_Code;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSecure_Code() {
		return secure_Code;
	}

	public void setSecure_Code(String secure_Code) {
		this.secure_Code = secure_Code;
	}
	
}
