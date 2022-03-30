package com.alexis.productservice.service;

import com.alexis.productservice.entity.Product;
import com.alexis.productservice.feignclients.CategoryFeignClient;
import com.alexis.productservice.models.Category;
import com.alexis.productservice.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    CategoryFeignClient categoryFeignClient;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Integer id){
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product cliente){
        return productRepository.save(cliente);
    }

    /*public List<Product> findByCategoryId(Integer categoryId){
        return productRepository.findByCategoryId(categoryId);
    }*/

    public Category getCategories(Integer productId){
        Category category = restTemplate.getForObject("http://localhost:8083/api/categorias/producto/"+productId, Category.class);
        return category;
    }

    public Category saveCategoryNew(Integer productId, Category category){
        category.setProductId(productId);
        Category categoryNew = categoryFeignClient.save(category);
        return categoryNew;
    }

}
