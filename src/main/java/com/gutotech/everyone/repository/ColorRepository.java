package com.gutotech.everyone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutotech.everyone.model.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

	List<Color> findAllByOrderByName();
}
