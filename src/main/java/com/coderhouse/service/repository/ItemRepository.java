package com.coderhouse.service.repository;

import com.coderhouse.service.model.Item;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends CrudRepository<Item, Long> {

    @Modifying
    @Query("update Item u set u.categoria = :categoria where u.stock = :stock")
    void updateCategory(@Param(value = "categoria") String categoria, @Param(value = "stock") int stock);

}
