package com.project.stationeryStore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.project.stationeryStore.domain.inventory.Brands;

public interface BrandsRepository extends JpaRepository<Brands, Integer>, PagingAndSortingRepository<Brands, Integer>{

	@Query("From Brands b where b.isActive = true")
	Page<Brands> findBrandsByIsAcitve(Pageable pageables);
	
	@Query("From Brands b where b.isActive = true and b.id = :id")
	Brands findBrandByIdAndIsActive(@Param("id") Integer id);
}
