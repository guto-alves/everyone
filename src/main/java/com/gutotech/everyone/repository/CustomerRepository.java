package com.gutotech.everyone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gutotech.everyone.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByEmail(String email);

	@Query("SELECT c FROM Customer c WHERE c.email = :email")
	Customer getCustomerByEmail(@Param("email") String email);

}
