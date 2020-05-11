package com.nivtek.invento.service;

import com.nivtek.invento.dao.SupplierRepository;
import com.nivtek.invento.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * @project Invento
 * @author AsimSubedi created on 5/10/2020
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<Supplier> findSupplier(int supplierid) {
        return supplierRepository.findById(supplierid);
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }
}
