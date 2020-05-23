package com.example.order.services;

import com.example.order.entities.Orders;
import com.example.order.entities.Status;
import com.example.order.repositories.OrdersRepository;
import com.example.order.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersService {

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    OrdersRepository ordersRepository;

    public List<Orders> getAllOrders(){
        return ordersRepository.getAll();
    }

    public Orders getOrderById(int id){
        return ordersRepository.getByID(id);
    }

    public Orders addNewOrder(Orders orders){
        if (orders.getStatus() != null) {
            Status status = statusRepository.getByName(orders.getStatus().getName());
            if (status != null) {
                orders.setStatus(status);
            }
        }
        return ordersRepository.add(orders);
    }

    public Orders updateOrder(int id, Orders orders){
        Status status = statusRepository.getByName(orders.getStatus().getName());
        if (status != null){
            orders.setStatus(status);
        }
        return ordersRepository.update(id, orders);
    }

    public Orders patchOrder(int id, String str){
        Status status = statusRepository.getByName(str);
        if (status != null){
            return ordersRepository.patch(id, status);
        } else {
            Status newStatus = new Status();
            newStatus.setName(str);
            return ordersRepository.patch(id, newStatus);
        }
    }

    public void remove(int id){
        ordersRepository.remove(id);
    }
}
