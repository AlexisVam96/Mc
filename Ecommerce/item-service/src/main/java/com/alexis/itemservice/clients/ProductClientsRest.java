package com.alexis.itemservice.clients;

import com.alexis.itemservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//@FeignClient(name = "producto-service", url = "http://localhost:8001", primary = false)
//USANDO RIBBON
@FeignClient(name = "product-service")
public interface ProductClientsRest {

    @GetMapping("/listar")
    public List<Product> getAll();

    @GetMapping("/{id}")
    public Product fingById(@PathVariable Integer id);

}
