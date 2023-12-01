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
@Table(name = "VIPs")
@Getter @Setter
public class VIPs extends BaseEntity{

	@Column(name = "price_from")
	private Float priceFrom;
	
	@Column(name = "discount")
	private Integer discount;
	
	@Column(name = "total_bill")
	private Float totalBill;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@OneToMany(mappedBy = "vip")
	private List<Users> users;
}
