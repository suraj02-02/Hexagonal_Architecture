package com.example.hexagonal.movies.demo.domain.entities;

import lombok.*;

/**
 *  Consider this as an Entity for Mysql DataBase
 *  Since we are not using any datasource to avoid unwanted complexity
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {

    private int orderId;
    private String orderOwnerName;
    private short productCount;
    private double orderPrice;
    private OrderStatusEnum orderStatus;
}
