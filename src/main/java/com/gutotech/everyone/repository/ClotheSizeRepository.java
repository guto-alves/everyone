package com.gutotech.everyone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutotech.everyone.model.ClotheSize;

@Repository
public interface ClotheSizeRepository extends JpaRepository<ClotheSize, Long>{

}
