package com.alexis.itemservice.service;

import com.alexis.itemservice.models.Item;
import com.alexis.itemservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService{

    @Autowired
    private RestTemplate restTemplate;

    public List<Item> getAll(){
        List<Product> products = Arrays.asList(restTemplate.getForObject("http://localhost:8001/api/productos",Product[].class) );

        //CAMBIAR LA LISTA DE PRODUCTOS A LISTA DE ITEM
        return products.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    public Item findById(Integer id, Integer quantity){
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        Product product = restTemplate.getForObject("http://localhost:8001/api/productos/{id}",Product.class, pathVariables) ;
        return new Item(product, quantity);
    }
}
