package com.example.order.repositories;

import com.example.order.entities.Orders;
import com.example.order.entities.Status;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class OrdersRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Orders> getAll(){
        return entityManager
                .createQuery("from Orders", Orders.class)
                .getResultList();
    }

    public Orders getByID(int id){
        return entityManager.find(Orders.class, id);
    }

    public Orders add(Orders orders){
        entityManager.persist(orders);
        return orders;
    }

    public Orders update(int id, Orders orders){
        Orders originalOrders = entityManager.find(Orders.class, id);
        if (originalOrders != null){
            originalOrders.setOfferId(orders.getOfferId());
            originalOrders.setName(orders.getName());
            originalOrders.setDeliverytime(orders.getDeliverytime());
            originalOrders.setStatus(orders.getStatus());
            originalOrders.setCustomerId(orders.getCustomerId());
            originalOrders.setPaid(orders.getPaid());

            entityManager.merge(originalOrders);
        }
        return originalOrders;
    }

    //patch
    public Orders patch(int id, Status status){
        Orders originalOrders = entityManager.find(Orders.class, id);
        if (originalOrders != null){
            originalOrders.setStatus(status);
        }
        return originalOrders;
    }

    public void remove(int id){
        Orders originalOrders = entityManager.find(Orders.class, id);
        if (originalOrders != null){
            entityManager.remove(originalOrders);
        }
    }
}
