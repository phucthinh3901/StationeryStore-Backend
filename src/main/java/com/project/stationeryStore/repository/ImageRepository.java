package com.project.stationeryStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.stationeryStore.domain.inventory.Images;

public interface ImageRepository extends JpaRepository<Images, Integer>{

}
