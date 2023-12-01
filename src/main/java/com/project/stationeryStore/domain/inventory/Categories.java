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
@Table(name = "categories")
@Getter @Setter
public class Categories extends BaseEntity{

	@Column(name = "categor_name")
	private String categoryName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "image")
	private String image;

	@Column(name = "is_active")
	private Boolean isActive;
	
	@OneToMany(mappedBy = "categories")
	private List<Products> product;
}
