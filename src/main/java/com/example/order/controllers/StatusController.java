package com.example.order.controllers;

import com.example.order.entities.Status;
import com.example.order.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/ordserv", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping("/statuses")
    public ResponseEntity<List<Status>> getAllStatuses() {
        return new ResponseEntity<>(statusService.getAllStatuses(), HttpStatus.OK);
    }

    @GetMapping("/statuses/{statusId}")
    public ResponseEntity<Status> getStatusByID(@PathVariable("statusId") int id) {
        if (statusService.getStatusById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(statusService.getStatusById(id), HttpStatus.OK);
    }

    @PostMapping("/statuses")
    public ResponseEntity<Status> addStatus(@RequestBody Status status){
        return new ResponseEntity<>(statusService.addNewStatus(status), HttpStatus.CREATED);
    }

    @PutMapping("/statuses/{statusId}")
    public ResponseEntity<Status> updateStatus(@PathVariable("statusId") int id,
                               @RequestBody Status status) {
        if (statusService.getStatusById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(statusService.updateStatus(id, status), HttpStatus.OK);
    }

    @DeleteMapping("/statuses/{statusId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteStatus(@PathVariable("statusId") int id) {
        if (statusService.getStatusById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        statusService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
