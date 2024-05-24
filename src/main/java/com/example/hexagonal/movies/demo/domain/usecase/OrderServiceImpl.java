package com.example.hexagonal.movies.demo.domain.usecase;

import com.example.hexagonal.movies.demo.adapters.requestdto.CreateNewOrder;
import com.example.hexagonal.movies.demo.adapters.responsedto.OrderResponse;
import com.example.hexagonal.movies.demo.domain.entities.OrderEntity;
import com.example.hexagonal.movies.demo.port.primary.OrderService;
import com.example.hexagonal.movies.demo.port.secondary.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *   This is service/domain layer where the actual business logic will be present for order service.
 *
 *   Domain layer is invoked by the adapter (primary adapter) based on the contract provided to it
 *   using the primary ports. If the adapter needs to interact with the domain layer then there must be some
 *   contract present in a port which the adapter is using.
 *
 *   This gives us a control over our domain layer from the adapters (primary / secondary).
 *   Further interaction with any external factors : database / queues / api will be done using
 *   secondary port/interface which will internally communicate with infrastructure layer.
 */

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public int createOrder(CreateNewOrder newOrder) {

        validateOrder(newOrder.getOrderId());
        this.orderRepository.save(newOrder);
        return newOrder.getOrderId();
    }

    @Override
    public OrderResponse getOrderById(int orderId) {
        // TODO supply a user defined exception
        return this.orderRepository.findOrderById(orderId).orElseThrow(null);
    }

    @Override
    public void deleteOrder(int orderId) {

        validateOrder(orderId);
        this.orderRepository.deleteOrderById(orderId);
    }

    private void validateOrder(int orderId) {
        
        boolean isOrderEmpty = this.orderRepository.findOrderById(orderId).isEmpty();
        if(isOrderEmpty){
            // throw new exception related to order already present
        }
    }

}
