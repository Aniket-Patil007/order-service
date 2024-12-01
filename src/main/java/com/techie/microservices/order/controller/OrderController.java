package com.techie.microservices.order.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techie.microservices.order.dto.OrderRequest;
import com.techie.microservices.order.model.Order;
import com.techie.microservices.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@PostMapping("/insert")
	@ResponseStatus(HttpStatus.CREATED)
	public String placeOrder(@RequestBody OrderRequest orderRequest) {
		orderService.placeOrder(orderRequest);
		return "Order Place Successfully";
		
	}
	
	@GetMapping("/fetchAll")
	@ResponseStatus(HttpStatus.OK)
	public List<Order> getOrder() {
		return orderService.getOrderDetails();
		
	}
	
	@GetMapping("/fetchById/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Order> getOrderById(@PathVariable(value="id") Long id) {
		
		return orderService.getOrderDetailsById(id);
		
	}
	
	@DeleteMapping("/deleteById/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteById(@PathVariable(value="id") Long id) {
		
		orderService.deleteOrderDetailsById(id);
		
	}
}
