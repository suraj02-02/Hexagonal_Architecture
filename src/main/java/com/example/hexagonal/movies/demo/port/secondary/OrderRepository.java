package com.example.hexagonal.movies.demo.port.secondary;

import com.example.hexagonal.movies.demo.adapters.requestdto.CreateNewOrder;
import com.example.hexagonal.movies.demo.adapters.responsedto.OrderResponse;
import com.example.hexagonal.movies.demo.domain.entities.OrderEntity;

import java.util.Optional;

/**
 *  This is an outbound / secondary port used as the contract between domain layer and
 *  secondary adapter which will interact with external factors : database / queues / filesystem
 *
 *  So , your application never knows where the data is going / coming from
 */

public interface OrderRepository {

    public void save(CreateNewOrder newOrder);
    public Optional<OrderResponse> findOrderById(int orderId);
    public void deleteOrderById(int orderId);
}
