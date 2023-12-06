package com.project.stationeryStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.stationeryStore.domain.inventory.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

	@Query("From Users u where u.isActive = true And u.id = :id")
	Users findUserByIdAndIsActive(@Param("id") Integer id);
}
