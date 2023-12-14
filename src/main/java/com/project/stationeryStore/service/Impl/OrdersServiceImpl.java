package com.project.stationeryStore.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.stationeryStore.domain.dto.OrderDetailsDto;
import com.project.stationeryStore.domain.dto.OrderDto;
import com.project.stationeryStore.domain.dto.ProductOrderDto;
import com.project.stationeryStore.domain.inventory.EOrderStatus;
import com.project.stationeryStore.domain.inventory.OrderDetails;
import com.project.stationeryStore.domain.inventory.Orders;
import com.project.stationeryStore.domain.inventory.Products;
import com.project.stationeryStore.domain.inventory.Users;
import com.project.stationeryStore.domain.payload.request.EmailPaymentNotificationRequest;
import com.project.stationeryStore.domain.payload.request.EmailUpdateStatusRequest;
import com.project.stationeryStore.domain.payload.request.OrderByOptionRequest;
import com.project.stationeryStore.domain.payload.request.StatusOrderRequest;
import com.project.stationeryStore.repository.CartRepository;
import com.project.stationeryStore.repository.OrderDetailsRepository;
import com.project.stationeryStore.repository.OrderRepository;
import com.project.stationeryStore.repository.ProductRepository;
import com.project.stationeryStore.repository.UserRepository;
import com.project.stationeryStore.service.EmailService;
import com.project.stationeryStore.service.OrdersService;


@Service
public class OrdersServiceImpl implements OrdersService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	EmailService emailService;
	
	@Override
	public List<OrderDto> createrOrderInCart(OrderByOptionRequest request) {
		
		Float totalBill = 0f;
		List<OrderDetails> orderDetailList = new ArrayList<OrderDetails>();
		List<OrderDto> result = new ArrayList<OrderDto>();
		EmailPaymentNotificationRequest email = new EmailPaymentNotificationRequest();
		
		Users user = userRepository.findUserByIdAndIsActive(request.getUserId());
		
		OrderDetails orderDetail = null;
		Orders order = new Orders();
		order.setAddress(request.getAddress());
		order.setIsActive(true);
		order.setUpdatedBy(null);
		order.setUser(user);
		order.setCreatedDate(new Date());
		order.setDiscountVIP(request.getDiscountVip());
		order.setDescription(request.getDescription());
		order.setPayment(request.getPayment());
		order.setRemark(request.getRemark());
		order.setStatus(EOrderStatus.CHOXACNHAN.toString());
		Products product = null;
		for(ProductOrderDto dto : request.getListProduct()){
			product = productRepository.findByProductById(dto.getProductId());
			totalBill += dto.getQuantity() *(product.getPrice() 
					- ((product.getPrice()*product.getDiscount())/100));
			
			orderDetail = new OrderDetails();
			orderDetail.setDiscount(product.getDiscount());
			orderDetail.setOrders(order);
			orderDetail.setPrice((product.getPrice() 
					- ((product.getPrice()*product.getDiscount())/100)));
			orderDetail.setQuanity(product.getQuanity());
			orderDetail.setProduct(product.getId());
			orderDetailList.add(orderDetail);
			product.setQuanity(product.getQuanity() - dto.getQuantity());
			
		}
		
		email.setAmount(totalBill);
		email.setContent(order.getDescription());
		email.setCreateAt(order.getCreatedDate());
		email.setToEmail(user.getEmail());
		email.setUserName(user.getUsername());
		
		email.setProductOrderDtos(request.getListProduct());
		
		order.setTotalBill(totalBill);
		
		orderRepository.save(order);
		
		orderDetailsRepository.saveAll(orderDetailList);
		
		emailService.sendMailUPpgradeRank(email);
		
		productRepository.save(product);
		
		cartRepository.deleteAll(user.getCartId());
		
		result = getOrderByUserId(user.getId());
		
		return result;
	}

	@Override
	public List<OrderDto> getOrderByUserId(Integer orderByUserId) {
		
		Users user = userRepository.findUserByIdAndIsActive(orderByUserId);
		OrderDto dto = null;
		OrderDetailsDto detailsDto = null;
		List<OrderDto> listDto = new ArrayList<OrderDto>();
		List<OrderDetailsDto> listDetailDto =  new ArrayList<OrderDetailsDto>();
		for(Orders order : user.getOrders()) {
			dto = new OrderDto();
			dto.setAddress(order.getAddress());
			dto.setId(order.getId());
			dto.setIsActive(order.getIsActive());
			dto.setUpdateAt(order.getUpdatedDate());
			dto.setCreateAt(order.getCreatedDate());
			dto.setUserId(order.getUser().getId());
			dto.setDiscountVip(order.getDiscountVIP());
			dto.setUpdateBy(order.getUpdatedBy());
			dto.setDescription(order.getDescription());
			dto.setPayment(order.getPayment());
			dto.setStatus(order.getStatus());
			dto.setTotalBill(order.getTotalBill());
			dto.setProductNumber(user.getCartId().size());
			List<OrderDetails> listDetail = orderDetailsRepository.findOrderDetailsByOrderId(order.getId());
			for(OrderDetails detail : listDetail) {
				detailsDto = new OrderDetailsDto();
				detailsDto.setDiscount(detail.getDiscount());
				detailsDto.setOrderId(detail.getOrders().getId());
				detailsDto.setOrderDetaiId(detail.getId());
				detailsDto.setPrice(detail.getPrice());
				detailsDto.setQuantity(detail.getQuanity());
				listDetailDto.add(detailsDto);
			}
			dto.setOrderDetailsDto(listDetailDto);
			listDto.add(dto);
		}
		
		return listDto;
	}

	@Override
	public List<OrderDto> updateOrderStatus(StatusOrderRequest request) {

		List<OrderDto> dto = new ArrayList<OrderDto>();
	
		EmailUpdateStatusRequest email = new EmailUpdateStatusRequest();
		
		Orders order = orderRepository.findOrderByOrderIdAndUserId(request.getOrderId(),request.getUserId());
		order.setStatus(request.getStatus());
		
		email.setCreateAt(new Date());
		email.setNameUser(order.getUser().getName());
		email.setOrderId(order.getId());
		email.setStatus(order.getStatus());
		email.setToEmail(order.getUser().getEmail());
		
		emailService.sendEmailUpdateStatus(email);
		
		orderRepository.save(order);
		
		dto = getOrderByUserId(request.getUserId());
		return dto;
		
	}
	@Override
	public List<Orders> getListOrder() {
		List<Orders> listOrder = orderRepository.findAll();
		return listOrder;
	}

	@Override
	public List<OrderDto> getDetailOrder(Integer orderId) {
		OrderDto dto = new OrderDto();
		List<OrderDto> listDto = new ArrayList<OrderDto>();
		List<OrderDetailsDto> listDetailDto =  new ArrayList<OrderDetailsDto>();
		OrderDetailsDto detailsDto = new OrderDetailsDto();
	
		Orders order = orderRepository.findOrderByIdAndIsActive(orderId);
		
		dto.setAddress(order.getAddress());
		dto.setId(order.getId());
		dto.setIsActive(order.getIsActive());
		dto.setUpdateAt(order.getUpdatedDate());
		dto.setCreateAt(order.getCreatedDate());
		dto.setUserId(order.getUser().getId());
		dto.setDiscountVip(order.getDiscountVIP());
		dto.setUpdateBy(order.getUpdatedBy());
		dto.setDescription(order.getDescription());
		dto.setPayment(order.getPayment());
		dto.setStatus(order.getStatus());
		dto.setTotalBill(order.getTotalBill());
		List<OrderDetails> listDetail = orderDetailsRepository.findOrderDetailsByOrderId(order.getId());
		for(OrderDetails detail : listDetail) {
			detailsDto = new OrderDetailsDto();
			detailsDto.setDiscount(detail.getDiscount());
			detailsDto.setOrderId(detail.getOrders().getId());
			detailsDto.setOrderDetaiId(detail.getId());
			detailsDto.setPrice(detail.getPrice());
			detailsDto.setQuantity(detail.getQuanity());
			listDetailDto.add(detailsDto);
		}
		dto.setOrderDetailsDto(listDetailDto);
		listDto.add(dto);
		return listDto;
	}

	@Override
	public List<Orders> filtListStutusOrder(String status) {
		List<Orders> order = orderRepository.findOrderByStatus(status);		
		return order;
	}
}















