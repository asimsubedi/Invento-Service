package com.nivtek.invento.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/*
 * @project Invento
 * @author AsimSubedi created on 5/8/2020
 */
@Data
@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    private int id;
    private String name;

    @OneToMany(mappedBy = "supplier")
    private Set<Product> product = new HashSet<Product>();
}
