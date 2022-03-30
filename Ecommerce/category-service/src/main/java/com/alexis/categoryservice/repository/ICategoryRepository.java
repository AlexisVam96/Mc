package com.alexis.categoryservice.repository;

import com.alexis.categoryservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {

    public Category findByProductId(Integer productId);
}
