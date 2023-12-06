package com.project.stationeryStore.domain.inventory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.stationeryStore.framework.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_details")
@Getter @Setter
public class OrderDetails extends BaseEntity{

	@Column(name = "price")
	private Float price;
	
	@Column(name = "quanity")
	private Integer quanity;
	
	@Column(name = "discount")
	private Integer discount;
	
	@Column(name = "product_Id")
	private Integer product;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Orders orders;
	
	
}
