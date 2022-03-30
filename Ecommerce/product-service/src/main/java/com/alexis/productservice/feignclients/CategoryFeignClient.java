package com.alexis.productservice.feignclients;

import com.alexis.productservice.models.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "category-service", url = "http://localhost:8083")
@RequestMapping("/api/categorias")
public interface CategoryFeignClient {

    @PostMapping()
    Category save(@RequestBody Category category);
}
