package com.alexis.productservice.controller;


import com.alexis.productservice.entity.Product;
import com.alexis.productservice.models.Category;
import com.alexis.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class  ProductController {

    @Autowired
    private ProductService productService;

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/listar")
    public ResponseEntity<List<Product>> getAll(){
        List<Product> products = productService.findAll().stream().map( producto ->{
            producto.setPort(port);
            return producto;
        }).collect(Collectors.toList());
        if(products.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") Integer id){
        Product product = productService.findById(id);
        product.setPort(port);

        /*
        try{
            Thread.sleep(2000L);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        */

        if(product == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(product);
    }

    /*@GetMapping("/productos/categoria/{categoryId}")
    public ResponseEntity<List<Product>> getByCategoryId(@PathVariable("categoryId") Integer categoryId){
        List<Product> products = productService.findByCategoryId(categoryId);
        if(products.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(products);
    }*/

    @PostMapping("/productos")
    public ResponseEntity<Product> save(@RequestBody Product product){
        Product productNew = productService.save(product);
        if(productNew == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(productNew);
    }

    @GetMapping("/productos/categoria/{productId}")
    public ResponseEntity<Category> getCategories(@PathVariable("productId") Integer productId){
        Product product = productService.findById(productId);
        if(product == null)
            return ResponseEntity.notFound().build();
        Category category = productService.getCategories(productId);
        if(category == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(category);

    }

    @PostMapping("/savecategory/{productId}")
    public ResponseEntity<Category> saveCategory(@PathVariable("productId") Integer productId, @RequestBody Category category){
        Category categoryNew = productService.saveCategoryNew(productId, category);
        if(categoryNew == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryNew);
    }
}
