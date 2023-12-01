package com.project.stationeryStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.project.stationeryStore.domain.inventory.Brands;

public interface BrandsRepository extends JpaRepository<Brands, Integer>, PagingAndSortingRepository<Brands, Integer>{

}
