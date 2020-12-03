package com.gutotech.everyone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutotech.everyone.model.Clothe;

@Repository
public interface ClotheRepository extends JpaRepository<Clothe, Long> {

}
