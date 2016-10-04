package com.code.generate.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "RESULT")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcessVerificationResponse {

	public int result_code;
	
	public boolean valid;

	public String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getResult_code() {
		return result_code;
	}

	public void setResult_code(int result_code) {
		this.result_code = result_code;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
