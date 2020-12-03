package com.gutotech.everyone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutotech.everyone.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
