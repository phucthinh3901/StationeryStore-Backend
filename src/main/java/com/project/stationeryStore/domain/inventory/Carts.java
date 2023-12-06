package com.project.stationeryStore.domain.inventory;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "carts")
@Getter @Setter
public class Carts {

	@Column(name = "quanity")
	private Integer quanity;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@EmbeddedId
	private CartId id;
	
	
}
