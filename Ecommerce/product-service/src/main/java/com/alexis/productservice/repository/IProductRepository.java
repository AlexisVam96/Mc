package com.alexis.productservice.repository;

import com.alexis.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

    //public List<Product> findByCategoryId(Integer categoryId);
}
