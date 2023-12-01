package com.project.stationeryStore.domain.inventory;

import com.project.stationeryStore.framework.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter @Setter
public class Roles extends BaseEntity{

	@Enumerated(EnumType.STRING)
	private ERoles name;
}
