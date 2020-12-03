package com.gutotech.everyone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutotech.everyone.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
