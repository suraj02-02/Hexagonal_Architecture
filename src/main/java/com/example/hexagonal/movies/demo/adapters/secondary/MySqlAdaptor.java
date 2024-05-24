package com.example.hexagonal.movies.demo.adapters.secondary;

import com.example.hexagonal.movies.demo.adapters.requestdto.CreateNewOrder;
import com.example.hexagonal.movies.demo.adapters.responsedto.OrderResponse;
import com.example.hexagonal.movies.demo.domain.entities.OrderEntity;
import com.example.hexagonal.movies.demo.domain.entities.OrderStatusEnum;
import com.example.hexagonal.movies.demo.port.secondary.OrderRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This is an outbound / secondary adapter which is responsible for interaction
 * b/w domain layer <-> Infra layer (MysqlDatabase in this case)
 *
 * So any changes in Infra layer (DB / Queues / External Api) will have no impact on the domain layer (Business logic of app)
 * and the switch over can be done with ease.
 */

@Primary
@Component
public class MySqlAdaptor implements OrderRepository {

    /**
     * Consider this as a @OrderEntity storage buffer since we have not configured any external database
     * for the ease of development.
     *
     */
    private static final Map<Integer , OrderEntity> orderEntityMap = new ConcurrentHashMap<>();

    @Override
    public void save(CreateNewOrder newOrder) {
          orderEntityMap.put(newOrder.getOrderId() , buildOrderEntity(newOrder));
    }

    private OrderEntity buildOrderEntity(CreateNewOrder newOrder) {

        return OrderEntity.builder()
                 .orderId(newOrder.getOrderId())
                 .orderOwnerName(newOrder.getOrderOwnerName())
                  // TODO Issue with source code dependency flowing outside domain layer
                 .orderStatus(OrderStatusEnum.CREATED)
                 .orderPrice(10012)
                 .build();
    }

    @Override
    public Optional<OrderResponse> findOrderById(int orderId) {

        OrderResponse orderResponse = Optional.ofNullable(orderEntityMap.get(orderId))
                .map(obj -> OrderResponse.builder()
                        .orderId(obj.getOrderId())
                        .orderStatus(obj.getOrderStatus().name())
                        .productCount((short) 8)
                        .build()
                ).orElse(null);

        return Optional.of(orderResponse);
    }

    @Override
    public void deleteOrderById(int orderId) {
        orderEntityMap.remove(orderId);
    }
}
