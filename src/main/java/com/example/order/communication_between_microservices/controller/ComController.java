package com.example.order.communication_between_microservices.controller;

import com.example.order.communication_between_microservices.dto.IdDto;
import com.example.order.communication_between_microservices.dto.OfferDto;
import com.example.order.communication_between_microservices.dto.OfferInfoDto;
import com.example.order.entities.Orders;
import com.example.order.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/ordserv", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComController {

    @Autowired
    OrdersService ordersService;

    //3.1
    @GetMapping("/offerinfo/{offerId}")
    public ResponseEntity<OfferInfoDto> getOfferInfo(@PathVariable("offerId") int id){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<OfferInfoDto> response = restTemplate.getForEntity(
                "http://127.0.0.1:8083/offserv/offers/" + id,
                OfferInfoDto.class
        );

        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    //3.2
    @PostMapping("/ordercreate")
    public ResponseEntity orderCreate(
            @RequestBody OfferDto offer,
            @RequestHeader("Authorization") String token
    ){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity entity = new HttpEntity(headers);

        IdDto idDto = restTemplate.exchange(
            "http://127.0.0.1:8081/custserv/customers/auth/token",
                HttpMethod.GET,
                entity,
                IdDto.class
        ).getBody();

        if (idDto == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        LocalDate localDate = LocalDate.now().plusDays(14);
        Date date = Date.valueOf(localDate);

        Orders order = new Orders();
        order.setOfferId(offer.getId());
        order.setName(offer.getName());
        order.setDeliverytime(date);
        order.setCustomerId(idDto.getId());
        order.setPaid(false);

        return new ResponseEntity(ordersService.addNewOrder(order), HttpStatus.CREATED);

    }
}
