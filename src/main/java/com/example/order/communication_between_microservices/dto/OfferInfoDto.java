package com.example.order.communication_between_microservices.dto;

import lombok.Data;

@Data
public class OfferInfoDto {
    private Integer id;
    private Float price;
    private Category category;
}
