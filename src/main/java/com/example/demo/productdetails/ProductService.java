package com.example.demo.productdetails;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("Product_Service")
public class ProductService {
  
	@Autowired
	ProductRepo product_rep;
	
	public void saveproduct(Product p) {
		
		product_rep.saveAndFlush(p);
	}
	public List<Product> displayproduct()
	{
		return product_rep.findAll();
	}
	
	public void deleteproductbyid(int id) {
		
		product_rep.deleteById(id);
	}
	public Product FindproductByid(int id) {
		return product_rep.getById(id);
	}
	public void updateproduct(Product p) {
		 product_rep.saveAndFlush(p);
	}
	@SuppressWarnings("deprecation") 
	public Product FindbyProductIdRemove(int em1) {
			  
			  return product_rep.findAll().stream().filter((m)->m.getStock_Unit() >
			  0).collect(Collectors.toList()).get(0);
			  
			  }
    public void updatestock(int stock_unit, int product_id) throws SQLException {
		
		
    	product_rep.stockupdate(stock_unit,product_id);
	}
}
