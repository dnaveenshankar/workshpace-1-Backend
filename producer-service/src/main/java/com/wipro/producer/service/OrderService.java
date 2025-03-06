package com.wipro.producer.service;
import com.wipro.producer.model.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	
	@Autowired
	private KafkaTemplate<String,Order> kafkaTemplate;

	public void sendOrder(Order order) {
		
		kafkaTemplate.send("order-topic", order);
	}
}
