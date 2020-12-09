package com.gutotech.everyone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.everyone.model.Product;
import com.gutotech.everyone.model.Review;
import com.gutotech.everyone.repository.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;

	public void save(Review review) {
		repository.save(review);
	}

	public List<Review> findAllByClothe(Product product) {
		return repository.findByProductOrderByIdDesc(product);
	}
}
