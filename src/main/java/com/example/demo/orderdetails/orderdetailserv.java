package com.example.demo.orderdetails;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderdetailserv")
public class orderdetailserv {
   @Autowired orderdetailsRepo orderdetailsRepo;
	
   public void savecheckout(orderdetails or) {
		
	   orderdetailsRepo.saveAndFlush(or);
		
	}
   public int getOrderNumber(){
	   
	   return (int)Math.random()*100;
   }
}
