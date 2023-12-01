package com.project.stationeryStore.domain.inventory;

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
@Table(name = "users")
@Getter @Setter
public class Users extends BaseEntity{

	@Column(name = "login_name")
	private String loginName;
	
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
	
	@OneToMany(mappedBy = "user")
	private List<Orders> orders;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vip_id")
	private VIPs vip;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Roles role;
}