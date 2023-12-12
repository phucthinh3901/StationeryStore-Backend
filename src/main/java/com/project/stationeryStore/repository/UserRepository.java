package com.project.stationeryStore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.stationeryStore.domain.inventory.Users;
import com.project.stationeryStore.domain.payload.response.UserResponse;

public interface UserRepository extends JpaRepository<Users, Integer>{

	@Query("From Users u where u.isActive = true And u.id = :id")
	Users findUserByIdAndIsActive(@Param("id") Integer id);
	
	@Query("From Users u where u.name = :usename")
	Optional<Users> findByUsername(String usename);
	
	@Query("From Users u where u.isActive = true")
	List<UserResponse> findAllUsers();
}
