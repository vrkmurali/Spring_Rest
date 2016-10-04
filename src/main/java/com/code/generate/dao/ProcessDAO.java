package com.code.generate.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ProcessDAO")
public class ProcessDAO  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, length = 10)
	private Integer id;
	
	@Column(name="USER_ID",nullable=false)
	private String userId;
	
	@Column(name="SECURE_CODE",nullable=false)
	private String secure_Code;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_ON", nullable = false)
	private Date created_On;
	
	
	public ProcessDAO() {
		super();
	}

	public ProcessDAO(String userId, String secure_Code) {
		super();
		this.userId = userId;
		this.secure_Code = secure_Code;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreated_On() {
		return created_On;
	}

	public void setCreated_On(Date created_On) {
		this.created_On = created_On;
	}

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
