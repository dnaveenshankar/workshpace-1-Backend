package com.wipro.orderservice.service;


import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

import com.wipro.orderservice.entity.Order;
import com.wipro.orderservice.entity.OrderItem;
import com.wipro.orderservice.exception.ResourceNotFoundException;
import com.wipro.orderservice.model.CustomerDTO;
import com.wipro.orderservice.model.InvoiceDTO;
import com.wipro.orderservice.model.MobileDTO;
import com.wipro.orderservice.model.OrderItemDTO;
import com.wipro.orderservice.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	//@Autowired
	//private RestTemplate restTemplate;
	
	@Autowired
	private CustomerApiClient customerApiClient;
	
	@Autowired
	private MobileApiClient mobileApiClient;

	@Override
	public Order placeOrder(int customerId, List<OrderItem> orderItems) {
		
		Order order = new Order();
		order.setOrderDate(LocalDate.now());
		order.setOrderStatus("Pending");
		order.setCustomerId(customerId);
		
		double orderTotal = 0;

		for (OrderItem item : orderItems) {

			int mobileId = item.getMobileId();
			int qty = item.getQty();

//			ResponseEntity<MobileDTO> responseEntity = restTemplate.
//					getForEntity("http://MOBILE-SERVICE/mobiles/" + mobileId, MobileDTO.class);
//			MobileDTO mobile = responseEntity.getBody();
			
			MobileDTO mobile = mobileApiClient.getMobileDetails(mobileId);
			
			double mobilePrice = mobile.getPrice();

			// calculate orderItem total
			double itemTotal = mobilePrice * qty;
			item.setItemTotal(itemTotal);
			
			orderTotal = orderTotal+itemTotal;
			
			item.setOrder(order);
		}

		order.setOrderAmount(orderTotal); 		
		order.setOrderItems(orderItems);

		orderRepository.save(order);

		return order;
	}

	@Override
	public Order getOrderDetails(int orderId) {
		
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isEmpty()) {
			throw new ResourceNotFoundException("Order not found");
		}

		Order order = optionalOrder.get();
		return order;
	}

	@Override
	public InvoiceDTO generateInvoice(int orderId) {
		
		Order order = getOrderDetails(orderId);
		
		InvoiceDTO invoice = new InvoiceDTO();
		invoice.setOrderId(order.getOrderId());
		invoice.setOrderDate(order.getOrderDate());
		invoice.setOrderAmount(order.getOrderAmount());
		invoice.setOrderStatus(order.getOrderStatus());
		
		int customerId = order.getCustomerId();
		
//		ResponseEntity<CustomerDTO> responseEntity = restTemplate.
//				getForEntity("http://localhost:8082/customer/"+customerId, CustomerDTO.class);
	
//		ResponseEntity<CustomerDTO> responseEntity = restTemplate.
//				getForEntity("http://CUSTOMER-SERVICE/customer/"+customerId, CustomerDTO.class);		
//		CustomerDTO customer = responseEntity.getBody();
		
		CustomerDTO customer = customerApiClient.getCustomerDetails(customerId);
		
		invoice.setCustomer(customer);
		
		List<OrderItem> orderEntityItems = order.getOrderItems();
		
		List<OrderItemDTO> orderItems = new ArrayList<>();
		
		for(OrderItem orderItem:orderEntityItems) {
			
			OrderItemDTO orderItemDTO = new OrderItemDTO();
			orderItemDTO.setOrderItemId(orderItem.getOrderItemId());
			orderItemDTO.setItemTotal(orderItem.getItemTotal());
			orderItemDTO.setQty(orderItem.getQty());
			
			int mobileId = orderItem.getMobileId();
			
//			ResponseEntity<MobileDTO> mobileResponseEntity = 
//					restTemplate.getForEntity("http://localhost:8081/mobiles/" + mobileId, MobileDTO.class);
			
//			ResponseEntity<MobileDTO> mobileResponseEntity = 
//					restTemplate.getForEntity("http://MOBILE-SERVICE/mobiles/" + mobileId, MobileDTO.class);		
//			MobileDTO mobile = mobileResponseEntity.getBody();
			
			MobileDTO mobile = mobileApiClient.getMobileDetails(mobileId);
					
			orderItemDTO.setMobile(mobile);
			
			orderItems.add(orderItemDTO);			
		}	
		
		invoice.setOrderItems(orderItems);
		
		return invoice;
	}
}
