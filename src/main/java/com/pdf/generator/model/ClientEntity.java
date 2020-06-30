package com.pdf.generator.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENT")
public class ClientEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cnmbr;

	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phnnmbr")
	private String phnnmbr;
	
	@Column(name = "dob")
	private Date dob;

	public ClientEntity() {
	}

	public ClientEntity(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}


	public Long getCnmbr() {
		return cnmbr;
	}


	public void setCnmbr(Long cnmbr) {
		this.cnmbr = cnmbr;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhnnmbr() {
		return phnnmbr;
	}


	public void setPhnnmbr(String phnnmbr) {
		this.phnnmbr = phnnmbr;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "ClientEntity [cnmbr=" + cnmbr + ", name=" + name + ", address=" + address + ", phnnmbr=" + phnnmbr
				+ ", dob=" + dob + "]";
	}

}