package com.gutotech.everyone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutotech.everyone.model.Customer;
import com.gutotech.everyone.model.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

	List<Sale> findByCustomer(Customer customer);

}
