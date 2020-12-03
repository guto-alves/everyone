package com.gutotech.everyone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutotech.everyone.model.SaleItem;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {

}
