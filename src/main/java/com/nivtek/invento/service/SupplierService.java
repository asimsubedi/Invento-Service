package com.nivtek.invento.service;

import com.nivtek.invento.model.Supplier;

import java.util.List;
import java.util.Optional;

/*
 * @project Invento
 * @author AsimSubedi created on 5/10/2020
 */
public interface SupplierService {
    /**
     * This method will return all the Suppliers along with their Supplier as a List.
     * If no Suppliers are present; This will return empty list.
     *
     * @return List<Supplier>
     */
    List<Supplier> getAllSuppliers();

    /**
     * @param Supplierid
     * @return Optional of Supplier
     */
    Optional<Supplier> findSupplier(int supplierid);

    /**
     * @param Supplier
     * @return Supplier Object that is saved
     */
    Supplier saveSupplier(Supplier supplier);
}
