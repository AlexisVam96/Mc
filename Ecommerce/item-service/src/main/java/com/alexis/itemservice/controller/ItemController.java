package com.alexis.itemservice.controller;

import com.alexis.itemservice.models.Item;
import com.alexis.itemservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    @Qualifier("serviceFeign")
    private ItemService itemService;

    @GetMapping("/listar")
    public ResponseEntity<List<Item>> getAll(@RequestParam(name = "nombre", required = false) String nombre,
                                             @RequestHeader(name = "token-request", required = false) String token){

        System.out.println(nombre);
        System.out.println(token);
        List<Item> items = itemService.getAll();
        if(items.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/ver/{id}/cantidad/{quantity}")
    public ResponseEntity<Item> getById(@PathVariable("id") Integer id,@PathVariable Integer quantity){
        Item item =itemService.findById(id, quantity);
        if(item == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(item);
    }
}
