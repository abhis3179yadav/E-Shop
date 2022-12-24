package com.example.demo.categories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;






@Service("category_Serv")
public class categoryServ {
	
	@Autowired 
	categoryRepo cate_rep;
	
		public void saverecord(Category c) {
			cate_rep.saveAndFlush(c);
		}
		public List<Category> displaycategory()
		{
			return cate_rep.findAll();
		}
		
		public void deletecategory(int id) {
			
			cate_rep.deleteById(id);
			
		}
		public Category FindByID(int id) {
		  return cate_rep.getById(id);
		}
		
		public void updateproduct(Category c) {

			System.out.println(c);

			cate_rep.saveAndFlush(c);

		}
		
		

}
