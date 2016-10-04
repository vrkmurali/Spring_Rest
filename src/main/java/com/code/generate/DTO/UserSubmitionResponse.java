package com.code.generate.DTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "RESULT")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSubmitionResponse {

	
	private Integer  result_code;

	private String message;

	private String secure_code;
	
	public String getSecure_code() {
		return secure_code;
	}
	
	public void setSecure_code(String secure_code) {
		this.secure_code = secure_code;
	}
	public Integer getResult_code() {
		return result_code;
	}

	public void setResult_code(Integer result_code) {
		this.result_code = result_code;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
