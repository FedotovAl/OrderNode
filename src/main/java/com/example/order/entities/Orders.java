package com.example.order.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "offerid")
    private Integer offerId;

    @Column(name = "name")
    private String name;

    @Column(name = "deliverytime")
    private Date deliverytime;

    //status
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "statusid")
    private Status status;

    @Column(name = "customerid")
    private Integer customerId;

    @Column(name = "paid")
    private Boolean paid;

}
