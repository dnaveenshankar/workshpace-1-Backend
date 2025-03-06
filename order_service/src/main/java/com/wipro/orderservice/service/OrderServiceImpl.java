package com.wipro.orderservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // @Autowired
    // private RestTemplate restTemplate;

    @Autowired
    private CustomerApiClient customerApiClient;

    @Autowired
    private MobileApiClient mobileApiClient;

    @Override
    public Order placeOrder(int customerIdD, List<OrderItem> orderItems) {

        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus("Pending");
        order.setCustomerId(customerIdD);

        double orderTotal = 0;

        for (OrderItem item : orderItems) {
            int mobileID = item.getMobileId();
            int qty = item.getQty();

            MobileDTO mobile = mobileApiClient.getMobileDetails(mobileID);
            // ResponseEntity<MobileDTO> responseEntity = restTemplate.getForEntity("http://MOBILE_SERVICE/mobiles/" + mobileID, MobileDTO.class);
            // MobileDTO mobile = responseEntity.getBody();

            double mobilePrice = mobile.getPrice();
            double itemTotal = mobilePrice * qty;
            item.setItemTotal(itemTotal);

            orderTotal += itemTotal;
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
        if (optionalOrder.isEmpty()) {
            throw new ResourceNotFoundException("Order not found with ID " + orderId);
        }

        return optionalOrder.get();
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

        CustomerDTO customer = customerApiClient.getCustomerDetails(customerId);
        // ResponseEntity<CustomerDTO> responseEntity = restTemplate.getForEntity("http://CUSTOMER_SERVICE/customer/" + customerId, CustomerDTO.class);
        // CustomerDTO customer = responseEntity.getBody();

        invoice.setCustomer(customer);

        List<OrderItem> orderEntityItems = order.getOrderItems();
        List<OrderItemDTO> orderItems = new ArrayList<>();

        for (OrderItem orderItem : orderEntityItems) {

            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setOrderItemId(orderItem.getOrderItemId());
            orderItemDTO.setItemTotal(orderItem.getItemTotal());
            orderItemDTO.setQty(orderItem.getQty());

            int mobileId = orderItem.getMobileId();

            MobileDTO mobile = mobileApiClient.getMobileDetails(mobileId);
            // ResponseEntity<MobileDTO> mobileResponseEntity = restTemplate.getForEntity("http://MOBILE_SERVICE/mobiles/" + mobileId, MobileDTO.class);
            // MobileDTO mobile = mobileResponseEntity.getBody();

            orderItemDTO.setMobile(mobile);
            orderItems.add(orderItemDTO);
        }

        invoice.setOrderItems(orderItems);
        return invoice;
    }
}
