package com.project.stationeryStore.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.stationeryStore.domain.inventory.Users;
import com.project.stationeryStore.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository usersRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Users user = usersRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("User not found for " + userName));
		return UserDetailsImpl.build(user);
	}
}
