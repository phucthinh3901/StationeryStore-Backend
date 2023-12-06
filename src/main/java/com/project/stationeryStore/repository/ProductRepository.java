package com.project.stationeryStore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.project.stationeryStore.domain.inventory.Products;

public interface ProductRepository extends JpaRepository<Products, Integer>, PagingAndSortingRepository<Products, Integer>{

	@Query("From Products p where p.isActive = true")
	Page<Products> findListProductByIsAcitveAndPagination(Pageable pageables);
	
	@Query("From Products p where p.isActive = true and p.id = :id")
	Products findByProductById(@Param("id") Integer id);
	
}
