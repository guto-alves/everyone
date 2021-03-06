package com.gutotech.everyone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutotech.everyone.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
