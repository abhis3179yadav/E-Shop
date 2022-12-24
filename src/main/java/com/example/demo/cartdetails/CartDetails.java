package com.example.demo.cartdetails;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cartdetails")
public class CartDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Product_no") 
	public int Product_no;
	@Column(name="product_name") 
	public String product_name;
	@Column(name="unit") 
	public int unit;
	@Column(name="amount")
	public long amount;
	@Column(name="added_by_user")
	public String AddedByUser;
	@Column(name="product_img")
	public String product_img;
	public String getProduct_img() {
		return product_img;
	}
	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}
	public int getProduct_no() {
		return Product_no;
	}
	public void setProduct_no(int product_no) {
		Product_no = product_no;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getAddedByUser() {
		return AddedByUser;
	}
	public void setAddedByUser(String addedByUser) {
		AddedByUser = addedByUser;
	}

	

}
