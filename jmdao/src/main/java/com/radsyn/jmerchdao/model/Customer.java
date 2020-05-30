package com.radsyn.jmerchdao.model;

import javax.persistence.*;

@Entity
@Table
public class Customer {
	
	public void populateWithObjectValues(Customer valueObj) {		
		 this.setCustID(valueObj.getCustID());
		 this.setAddr1(valueObj.getAddr1());
		 this.setAddr2(valueObj.getAddr2());
		 this.setCity(valueObj.getCity());
		 this.setCountry(valueObj.getCountry());
		 this.setEmail(valueObj.getEmail());
		 this.setFirstName(valueObj.getFirstName());
		 this.setLastName(valueObj.getLastName());
		 this.setNickName(valueObj.getNickName());
		 this.setPhone1(valueObj.getPhone1());
		 this.setPhone2(valueObj.getPhone2());
		 this.setPhone3(valueObj.getPhone3());
		 this.setPostcode(valueObj.getPostcode());
		 this.setStateprov(valueObj.getStateprov());
		 this.setDob(valueObj.getDob());
		 this.setAnniversary(valueObj.getAnniversary());
	 }
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long custID;	
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "nickname")
	private String nickName;
	
	@Column
	private String email;
	
	@Column
	private String phone1;

	@Column
	private String phone2;
	
	@Column
	private String phone3;

	@Column
	private String addr1;
	
	@Column
	private String addr2;
	
	@Column
	private String city;
	
	@Column
	private String stateprov;
	
	@Column
	private String country;
	
	@Column
	private String postcode;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date createdt;

	@Basic
	@Temporal(TemporalType.DATE)
	private java.util.Date dob;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private java.util.Date anniversary;
	
	public long getCustID() {
		return custID;
	}

	public void setCustID(long custID) {
		this.custID = custID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateprov() {
		return stateprov;
	}

	public void setStateprov(String stateprov) {
		this.stateprov = stateprov;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public java.util.Date getCreatedt() {
		return createdt;
	}

	public void setCreatedt(java.util.Date createdt) {
		this.createdt = createdt;
	}

	public java.util.Date getDob() {
		return dob;
	}

	public void setDob(java.util.Date dob) {
		this.dob = dob;
	}

	public java.util.Date getAnniversary() {
		return anniversary;
	}

	public void setAnniversary(java.util.Date anniversary) {
		this.anniversary = anniversary;
	}

	public String getDobStr() {
		if (getDob() == null) {
			return null;
		}
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(getDob());
		String locstr = String.valueOf(cal.get(java.util.Calendar.YEAR)) + "-" + 
				String.valueOf(cal.get(java.util.Calendar.MONTH) + 1) + "-" + 
				String.valueOf(cal.get(java.util.Calendar.DATE));
		return locstr;
	}

	public void setDobStr(String dobStr) {
		String[] dateParts = null;
		if (dobStr != null) {
			dateParts = dobStr.split("-");
		}
		if (dateParts != null && dateParts.length == 3) {
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.set(Integer.parseInt(dateParts[0]),(Integer.parseInt(dateParts[1]) - 1),Integer.parseInt(dateParts[2]));
			setDob(cal.getTime());
		}
	}
	
}
