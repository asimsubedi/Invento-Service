package com.nivtek.invento.model;

import lombok.Data;

import javax.persistence.*;

/*
 * @project Invento
 * @author AsimSubedi created on 5/8/2020
 */
@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String description;
    private float price;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

}
