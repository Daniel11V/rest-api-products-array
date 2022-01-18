package com.coderhouse.service.controller;

import com.coderhouse.service.handle.ApiRestException;
import com.coderhouse.service.model.Item;
import com.coderhouse.service.service.ItemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public List<Item> findItems() { return itemService.findAll(); }

    @GetMapping("/{id}")
    public Item findById(@PathVariable Long id) { return itemService.findById(id); }

    @PostMapping("/")
    public Item createItem(@RequestBody Item newItem) { return itemService.createItem(newItem); }

    @PutMapping("/{id}")
    public Item updateItemById(@PathVariable Long id, @RequestBody Item newItem) {
        return itemService.updateItemById(newItem, id);
    }

    @DeleteMapping("/{id}")
    public List<Item> deleteItemById(@PathVariable Long id) { return itemService.deleteItemById(id); }
}
