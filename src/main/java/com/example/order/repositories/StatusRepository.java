package com.example.order.repositories;

import com.example.order.entities.Status;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class StatusRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Status> getAll(){
        return entityManager
                .createQuery("from Status", Status.class)
                .getResultList();
    }

    public Status getByID(int id){
        return entityManager.find(Status.class, id);
    }

    public Status add(Status status){
        entityManager.persist(status);
        return status;
    }

    public Status update(int id, Status status){
        Status originalStatus = entityManager.find(Status.class, id);
        if (originalStatus != null){
            originalStatus.setName(status.getName());

            entityManager.merge(originalStatus);
        }
        return originalStatus;
    }

    public void remove(int id){
        Status originalStatus = entityManager.find(Status.class, id);
        if (originalStatus != null && originalStatus.getOrdersSetS().isEmpty()){
            entityManager.remove(originalStatus);
        }
    }

    public boolean isEmptyByName(String paramName) throws NullPointerException{
        List<Status> originalStatus = entityManager
                .createQuery("from Status where name = '"
                                + paramName +
                                "'",
                        Status.class
                )
                .getResultList();
        if (originalStatus.isEmpty()){
            return true;
        } else{
            return false;
        }
    }

    public Status getByName(String paramName){
        if (paramName == null){
            return null;
        }
        List<Status> statusesList = entityManager
                .createQuery("from Status where name = '"
                                + paramName +
                                "'",
                        Status.class
                ).getResultList();
        if (statusesList.size() != 0){
            return statusesList.get(0);
        } else {
            return null;
        }
    }


}
