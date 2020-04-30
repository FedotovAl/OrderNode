package com.example.order.services;

import com.example.order.entities.Status;
import com.example.order.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public List<Status> getAllStatuses(){
        return statusRepository.getAll();
    }

    public Status getStatusById(int id){
        return statusRepository.getByID(id);
    }

    public Status addNewStatus(Status status){
        if (statusRepository.isEmptyByName(status.getName())){
            return statusRepository.add(status);
        } else {
            return null;
        }
    }

    public Status updateStatus(int id, Status status){
        if (statusRepository.isEmptyByName(status.getName())){
            return statusRepository.update(id, status);
        } else {
            return null;
        }
    }

    public void remove(int id){
        statusRepository.remove(id);
    }
}
