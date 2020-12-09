package com.gutotech.everyone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutotech.everyone.model.Category;
import com.gutotech.everyone.model.Gender;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findAllByOrderByName();
	
	List<Category> findByGenderIn(List<Gender> genders);
}
