package com.project.stationeryStore.domain.inventory;

import java.util.Date;
import java.util.List;

import com.project.stationeryStore.framework.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Orders extends BaseEntity{

	@Column(name = "status")
	private String status;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "address")
	private Integer address;
	
	@Column(name = "total_bill")
	private Float totalBill;
	
	@Column(name = "payment")
	private String payment;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "discount_VIP")
	private Date discountVIP;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "remark")
	private String remark;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@OneToMany(mappedBy = "orders")
	private List<OrderDetails> orderDetails;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Users user;
}
