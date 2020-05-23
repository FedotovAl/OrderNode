package com.example.order.communication_between_microservices.dto;

import lombok.Data;

@Data
public class OfferDto {
    private Integer id;
    private String name;
    private Float price;
    private Integer paidTypeID;
    private Category category;

}
