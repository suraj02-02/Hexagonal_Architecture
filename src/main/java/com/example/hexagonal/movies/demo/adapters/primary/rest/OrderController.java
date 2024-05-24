package com.example.hexagonal.movies.demo.adapters.primary.rest;

import com.example.hexagonal.movies.demo.adapters.requestdto.CreateNewOrder;
import com.example.hexagonal.movies.demo.port.primary.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Suraj.Yadav
 * This is a primary rest adapter to perform action on Order resource.
 * Being a primary adapter it is responsible for interaction with the external drivers
 *
 * It will adapt the request from external drivers like : UI interface , external Apis
 * and will pass it on to the domain layer using the defined ports/interface inside application (domain layer)
 *
 */

@RequestMapping("/order")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * Endpoint invoked to create a new order resource using the newOrder request
     *
     * @param newOrder
     * @return
     */
    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody CreateNewOrder newOrder) {
        return new ResponseEntity<>(orderService.createOrder(newOrder) , HttpStatus.CREATED);
    }

    /**
     * Endpoint invoked to fetch an order details using orderId
     *
     * @param orderId
     * @return
     */
    @GetMapping("id/{orderId}")
    public ResponseEntity<?> getOrderUsingId(@PathVariable int orderId){
        return new ResponseEntity<>(orderService.getOrderById(orderId) , HttpStatus.OK);
    }

    /**
     * Endpoint invoked to delete an order using orderId
     *
     * @param orderId
     * @return
     */
    @DeleteMapping("/id/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable int orderId){
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
