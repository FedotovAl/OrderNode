package com.example.order.controllers;

import com.example.order.entities.Orders;
import com.example.order.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ordserv", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> getAllOrders(){
        return new ResponseEntity<>(ordersService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/orders/{ordersId}")
    public ResponseEntity<Orders> getOrderByID(@PathVariable("ordersId") int id){
        if (ordersService.getOrderById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersService.getOrderById(id), HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<Orders> addOrderr(@RequestBody Orders orders){
        return new ResponseEntity<>(ordersService.addNewOrder(orders), HttpStatus.CREATED);
    }

    @PutMapping("/orders/{ordersId}")
    public ResponseEntity<Orders> updateOrder(@PathVariable("ordersId") int id,
                                   @RequestBody Orders orders){
        if (ordersService.getOrderById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersService.updateOrder(id, orders), HttpStatus.OK);
    }

    @PatchMapping("/orders/{ordersId}")
    public ResponseEntity<Orders> patchOrder(@PathVariable("ordersId") int id, @RequestBody String statusParam){
        if (ordersService.getOrderById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersService.patchOrder(id, statusParam), HttpStatus.OK);
    }

    @DeleteMapping("/orders/{ordersId}")
    public ResponseEntity<?> deleteOrder(@PathVariable("ordersId") int id){
        if (ordersService.getOrderById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ordersService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
