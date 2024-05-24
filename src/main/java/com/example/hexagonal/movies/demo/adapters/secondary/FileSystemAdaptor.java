package com.example.hexagonal.movies.demo.adapters.secondary;


import com.example.hexagonal.movies.demo.adapters.requestdto.CreateNewOrder;
import com.example.hexagonal.movies.demo.adapters.responsedto.OrderResponse;
import com.example.hexagonal.movies.demo.domain.entities.OrderEntity;
import com.example.hexagonal.movies.demo.domain.entities.OrderStatusEnum;
import com.example.hexagonal.movies.demo.port.secondary.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 *  This is an outbound/secondary adapter which can be used by domain layer if they want to switch over from
 *  Mysql Persistence system to fileSystem based persistence.
 *
 *  This adapter works on the contract defined by secondary port #OrderRepository
 *
 */


@Component
public class FileSystemAdaptor implements OrderRepository {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String PERSISTING_FILE_PATH = "/path-to-file/";

    @Override
    public void save(CreateNewOrder newOrder) {

        try {
            mapper.writeValue(
                    new File(PERSISTING_FILE_PATH) , buildOrderEntity(newOrder)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<OrderResponse> findOrderById(int orderId) {
        /**
         * invalid method for file system.
         * Considering that it will have a performance issue in object parsing
         */
        return Optional.empty();
    }

    @Override
    public void deleteOrderById(int orderId) {
       // invalid method for file system
    }

    private static OrderEntity buildOrderEntity(CreateNewOrder newOrder) {

        return OrderEntity.builder()
                .orderId(newOrder.getOrderId())
                .orderOwnerName(newOrder.getOrderOwnerName())
                .orderStatus(OrderStatusEnum.CREATED)
                .orderPrice(newOrder.getOrderPrice())
                .productCount(newOrder.getProductCount())
                .build();
    }

}
