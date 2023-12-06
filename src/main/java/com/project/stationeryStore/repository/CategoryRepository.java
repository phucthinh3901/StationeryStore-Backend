package com.project.stationeryStore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.project.stationeryStore.domain.inventory.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Integer>, PagingAndSortingRepository<Categories, Integer>{

	@Query("From Categories c where c.isActive = true")
	Page<Categories> findCategoriesByIsAcitve(Pageable pageables);

	@Query("From Categories c where c.isActive = true and c.id = :id")
	Categories findCategoryByIdAndIsActive(@Param("id") Integer id);
}
