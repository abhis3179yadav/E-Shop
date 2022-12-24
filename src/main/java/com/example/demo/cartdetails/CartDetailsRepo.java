package com.example.demo.cartdetails;

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
public interface  CartDetailsRepo extends JpaRepository<CartDetails,Integer> {
	@Modifying
	@Transactional
	@Query(value= "{call DeleteRecord(:added_by_user)}",nativeQuery=true)
	 public void DeleteRecord(@Param("added_by_user")String added_by_user) throws SQLException;



}
