package com.gutotech.everyone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutotech.everyone.model.Clothe;
import com.gutotech.everyone.model.Customer;
import com.gutotech.everyone.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findByCustomer(Customer customer);

	List<Review> findByClotheOrderByIdDesc(Clothe clothe);

}
