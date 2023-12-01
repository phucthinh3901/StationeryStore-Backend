package com.project.stationeryStore.domain.inventory;

import java.util.List;

import com.project.stationeryStore.framework.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "brands")
@Getter @Setter
public class Brands extends BaseEntity{

	@Column(name = "brand_name")
	private String brandName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@OneToMany(mappedBy = "brands")
	private List<Products> product;
}
