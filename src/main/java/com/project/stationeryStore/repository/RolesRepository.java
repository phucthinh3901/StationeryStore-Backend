package com.project.stationeryStore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.stationeryStore.domain.inventory.ERoles;
import com.project.stationeryStore.domain.inventory.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer>{

	Optional<Roles> findByName (ERoles name);
}
