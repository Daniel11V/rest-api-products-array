package com.coderhouse.service.service;

import com.coderhouse.service.handle.ApiRestException;
import com.coderhouse.service.model.Client;
import com.coderhouse.service.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> findAll();
    Item findById(Long id);
    Item createItem(Item newItem);
    Item updateItemById(Item newItem, Long id);
    List<Item> deleteItemById (Long id);

}
