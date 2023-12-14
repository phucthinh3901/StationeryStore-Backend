package com.project.stationeryStore.domain.inventory;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "users")
@Getter @Setter
public class Users extends BaseEntity{

	@Column(name = "username")
	private String username;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "tel_num")
	private String telNum;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "address_code")
	private String addressCode;
	
	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "password_salt")
	private String passwordSalt;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Orders> orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "id.users")
	private List<Carts> cartId;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vip_id")
	private VIPs vip;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Roles role;
	
	@JsonIgnore
	@OneToMany(mappedBy = "feedbackId.users")
	private List<Feedbacks> feedbacks;
}
