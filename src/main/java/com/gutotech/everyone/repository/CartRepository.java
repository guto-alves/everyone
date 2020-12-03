package com.gutotech.everyone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutotech.everyone.model.Cart;
import com.gutotech.everyone.model.Customer;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	Cart findByCustomer(Customer customer);

}
