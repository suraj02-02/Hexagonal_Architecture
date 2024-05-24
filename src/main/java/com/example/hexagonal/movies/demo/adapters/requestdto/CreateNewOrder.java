package com.example.hexagonal.movies.demo.adapters.requestdto;

import com.example.hexagonal.movies.demo.domain.entities.OrderStatusEnum;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewOrder {

    private int orderId;
    private String orderOwnerName;
    private short productCount;
    private double orderPrice;
    private OrderStatusEnum orderStatus;
}
