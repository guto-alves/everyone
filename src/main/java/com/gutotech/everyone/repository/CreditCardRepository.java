package com.gutotech.everyone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutotech.everyone.model.CreditCard;
import com.gutotech.everyone.model.Customer;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

	List<CreditCard> findByCustomer(Customer customer);

}
