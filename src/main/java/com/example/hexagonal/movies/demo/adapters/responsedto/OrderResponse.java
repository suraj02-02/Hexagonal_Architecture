package com.example.hexagonal.movies.demo.adapters.responsedto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private int orderId;
    private String orderOwnerName;
    private short productCount;
    private double orderPrice;
    private String orderStatus;
}
