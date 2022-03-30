package com.alexis.categoryservice.controller;

import com.alexis.categoryservice.entity.Category;
import com.alexis.categoryservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categorias")
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = categoryService.findAll();
        if (categories.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id") Integer id) {
        Category category = categoryService.findById(id);
        if (category == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(category);
    }

    @PostMapping("/categorias")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        Category categoryNew = categoryService.save(category);
        if (categoryNew == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(categoryNew);
    }

    @GetMapping("/categorias/producto/{productId}")
    public ResponseEntity<Category> getProductId(@PathVariable("productId") Integer productId){
        Category category = categoryService.getProductId(productId);
        if (category == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(category);
    }
}
