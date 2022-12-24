package com.example.demo.orderdetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orderdetails")
public class orderdetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_no")
	int order_no;
	@Column(name="first_name")
	String First_name;
	@Column(name="last_name")
	String last_name;
	@Column(name="Address")
	String Address;
	@Column(name="postcode")
	int postcode;
	@Column(name="city")
	String city;
	@Column(name="phone")
	int phone;
	@Column(name="Additional_information")
	String Additional_information;
	public String getFirst_name() {
		return First_name;
	}
	public void setFirst_name(String first_name) {
		this.First_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getAdditional_information() {
		return Additional_information;
	}
	public void setAdditional_information(String additional_information) {
		Additional_information = additional_information;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	
}
