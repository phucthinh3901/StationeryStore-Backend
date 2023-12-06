package com.project.stationeryStore.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.stationeryStore.domain.dto.CartDto;
import com.project.stationeryStore.domain.inventory.CartId;
import com.project.stationeryStore.domain.inventory.Carts;
import com.project.stationeryStore.domain.inventory.Products;
import com.project.stationeryStore.domain.inventory.Users;
import com.project.stationeryStore.domain.payload.request.CartRequest;
import com.project.stationeryStore.domain.payload.request.RemoveCartRequest;
import com.project.stationeryStore.repository.CartRepository;
import com.project.stationeryStore.repository.ProductRepository;
import com.project.stationeryStore.repository.UserRepository;
import com.project.stationeryStore.service.CartService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<CartDto> createCart(CartRequest request) {
		
		Carts cart = null;
		cart = cartRepository.findProductInCartByUserIdAndProductId(request.getUserId(), request.getProductId());
		Users user = userRepository.findUserByIdAndIsActive(request.getUserId());
		Products product = productRepository.findByProductById(request.getProductId());
		CartId cartId = new CartId();
		List<CartDto> result = new ArrayList<CartDto>();
		if(cart == null) {
			 cart = new Carts();
			cart.setCreatedDate(new Date());
			cart.setUpdatedDate(null);
			cart.setQuanity(request.getQuantity());
		}
		if(cart != null) {
			cart.setUpdatedDate(new Date());
			cart.setQuanity(cart.getQuanity() + request.getQuantity());
		}
		cartId.setProducts(product);
		cartId.setUsers(user);
		cart.setId(cartId);
		cartRepository.save(cart);
		
		result = getCartByUserId(request.getUserId());
		return result;
	}
	@Override
	public List<CartDto> getCartByUserId(Integer cartByUserId) {
		
		Users user = userRepository.findUserByIdAndIsActive(cartByUserId);
		CartDto dto = null;
		List<CartDto> listDto = new ArrayList<CartDto>();
		for(Carts cart : user.getCartId()) {
			dto = new CartDto();
			dto.setId(cart.getId().getProducts().getId());
			dto.setCategoryName(cart.getId().getProducts().getCategories().getCategoryName());
			dto.setProductName(cart.getId().getProducts().getProductName());
			dto.setDiscount(cart.getId().getProducts().getDiscount());
			dto.setPrice(cart.getId().getProducts().getPrice());
			dto.setQuantity(cart.getId().getProducts().getQuanity());
			dto.setPriceAfterDiscount(cart.getId().getProducts().getQuanity() * (cart.getId().getProducts().getPrice()
					- (cart.getId().getProducts().getPrice()*cart.getId().getProducts().getDiscount())/100));
			listDto.add(dto);
		}
		return listDto;
	}
	@Override
	public List<CartDto> removeProductInCartByProductId(RemoveCartRequest request) {
		List<CartDto> result = new ArrayList<CartDto>();
		Carts cart = cartRepository.findProductInCartByUserIdAndProductId(request.getUserId(), request.getProductId());
		if(cart != null) {
			cartRepository.delete(cart);
		}		
		result = getCartByUserId(request.getUserId());
		return result;
	}

}
