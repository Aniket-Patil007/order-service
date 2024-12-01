package com.techie.microservices.order.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.techie.microservices.order.client.InventoryClient;
import com.techie.microservices.order.dto.OrderRequest;
import com.techie.microservices.order.model.Order;
import com.techie.microservices.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final InventoryClient inventoryClient;

	public void placeOrder(OrderRequest orderRequest) {
		
	var isProductInStock=inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
		
	if(isProductInStock) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		order.setPrice(orderRequest.price());
		order.setQuantity(orderRequest.quantity());
		order.setSkuCode(orderRequest.skuCode());
		orderRepository.save(order);
	}else {
		log.error("Product with SkuCode " +orderRequest.skuCode()+" is not in Stock");
	}
		
	}
	
	public List<Order> getOrderDetails() {
		orderRepository.findAll();
		return orderRepository.findAll();
		
	}
	
	public Optional<Order> getOrderDetailsById(Long id) {
		
		log.info("Fetching order for id: " +id);
		return orderRepository.findById(id);
	}
	
	public void deleteOrderDetailsById(Long id) {
		
		orderRepository.deleteById(id);
		log.info("deleted order for id: " +id);
	}
	
	
}
