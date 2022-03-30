package com.alexis.itemservice.service;

import com.alexis.itemservice.models.Item;

import java.util.List;

public interface ItemService {

    public List<Item> getAll();

    public Item findById(Integer id, Integer quantity);
}
