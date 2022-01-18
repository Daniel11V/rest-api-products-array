package com.coderhouse.service.service;

import com.coderhouse.service.model.Client;
import com.coderhouse.service.model.Item;
import com.coderhouse.service.repository.ItemRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepository repository;

    @Override
    public List<Item> findAll() {
        List<Item> itemList =  new ArrayList<>();
        repository.findAll().forEach(itemList::add);
        return itemList;
    }

    @Override
    public Item findById(Long id) { return repository.findById(id).get(); }

    @Override
    public Item createItem(Item newItem) { return repository.save(newItem); }

    @Override
    public Item updateItemById(@NotNull Item newItem, Long id) {
        newItem.setId(id);
        return repository.save(newItem);
    }

    @Override
    public List<Item> deleteItemById (Long id) {
        repository.deleteById(id);
        return this.findAll();
    }


}
