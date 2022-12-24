package com.example.demo.cartdetails;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service("CartDetails")
public class CartdetailsServ {
 
	@Autowired CartDetailsRepo cart_repo;
	
	public long tot=0;
	public int unit=0;
	
	public void savercartdetails(CartDetails c) {
		cart_repo.saveAndFlush(c);
	}
	public void RemoveFromCart(int id) {
		tot=0;
		unit=0;
		cart_repo.deleteById(id);
		
	}
	@SuppressWarnings("deprecation")
	public ListIterator<CartDetails> FilterRecordAddedByUser(String em) {
		 tot=0;
		 unit=0;
		cart_repo.findAll().stream().filter((m)->m.getAddedByUser().equals(em)).collect(Collectors.toList())
				.forEach((m)->{tot+=m.getAmount();unit+=m.getUnit();}
				);
						
		return cart_repo.findAll().stream().filter((m)->m.getAddedByUser().equals(em)).collect(Collectors.toList()).listIterator();
	
	}
	
	
	public void DeleteRecord(String added_by_user) throws SQLException {
		
		
		 cart_repo.DeleteRecord(added_by_user);
	}
}
