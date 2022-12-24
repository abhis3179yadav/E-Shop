package com.example.demo.productdetails;

import java.sql.SQLException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@EnableJpaRepositories
public interface ProductRepo extends JpaRepository<Product ,Integer>{
	@Modifying
	@Transactional
	@Query(value= "{call stockupdate(:stock_unit,:product_id)}",nativeQuery=true)
	 public void stockupdate(@Param("stock_unit")int balance_unit,@Param("product_id")int product_id) throws SQLException ;


}
