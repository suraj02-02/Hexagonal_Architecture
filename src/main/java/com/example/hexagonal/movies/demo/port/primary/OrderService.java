package com.example.hexagonal.movies.demo.port.primary;

import com.example.hexagonal.movies.demo.adapters.requestdto.CreateNewOrder;
import com.example.hexagonal.movies.demo.adapters.responsedto.OrderResponse;

/**
 * This is an inbound port / interface for order service
 *
 * It defines certain contracts based on which the adapters will interact with the
 * domain layer of application
 */

public interface OrderService {

        public int createOrder(CreateNewOrder newOrder);
        public OrderResponse getOrderById(int orderId);
        public void deleteOrder(int orderId);
}
