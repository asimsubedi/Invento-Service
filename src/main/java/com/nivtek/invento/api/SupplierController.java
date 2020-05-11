package com.nivtek.invento.api;

import com.nivtek.invento.dao.SupplierRepository;
import com.nivtek.invento.exceptions.ResourceNotFoundException;
import com.nivtek.invento.model.Supplier;
import com.nivtek.invento.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @project Invento
 * @author AsimSubedi created on 5/8/2020
 */
@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private SupplierRepository supplierRepository;

    /**
     * @return List of Product Objects. If no Products are present then it returns empty list.
     */
    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    /**
     * @param supplierId
     * @return Supplier Object. If no supplier is found; throw Supplier Not Found Exception!
     */
    @GetMapping("{id}")
    public Supplier getSupplier(@PathVariable(value = "id") int supplierId) {
        return this.supplierService.findSupplier(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("Oops! Supplier Not found with id : " + supplierId));
    }

    /**
     * @param supplier
     * @return Supplier Object we want to save
     */
    @PostMapping
    public Supplier saveSupplier(@RequestBody Supplier supplier) {
        return supplierService.saveSupplier(supplier);
    }

    @PutMapping("{id}")
    public Supplier updateSupplier(@PathVariable(value = "id") int id, @RequestBody Supplier supplierPost) {
        return supplierRepository.findById(id).map(supplier -> {
            supplier.setName(supplierPost.getName());
            return supplierRepository.save(supplier);
        }).orElseThrow(() -> new ResourceNotFoundException("id " + id + " Not Found!!! Sorry!!!"));
    }
}
