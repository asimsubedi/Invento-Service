package com.nivtek.invento.dao;

import com.nivtek.invento.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * @project Invento
 * @author AsimSubedi created on 5/10/2020
 */
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
