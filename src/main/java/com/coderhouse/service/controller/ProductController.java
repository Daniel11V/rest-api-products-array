package com.coderhouse.service.controller;

import com.coderhouse.service.handle.ApiRestException;
import com.coderhouse.service.model.Product;
import com.coderhouse.service.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    Logger logger = LogManager.getLogger(ProductController.class);

    private List<Product> products = new ArrayList<Product>() {
        {
            add(new Product(1L, "shoes", 100));
            add(new Product(2L, "shoes", 100));
            add(new Product(3L, "shoes", 100));
            add(new Product(4L, "shirt", 100));
            add(new Product(5L, "watch", 100));
        }
    };

    @Autowired
    private PersonService personService;

    @GetMapping("/products/user")
    public String getProductsString() {

        logger.info("GET Request recibido string");

        return personService.nombresCompletos();
    }

    @GetMapping("/products")
    public List<Product> getProductsAll() throws ApiRestException{

        logger.info("GET Request recibido string");

        if (this.products.size() == 0) {
            throw new ApiRestException("no hay productos cargados");
        }

        return this.products;
    }

//    @GetMapping("/products")
//    public List<Product> getProductsByDescription(@RequestParam String title) {
//        logger.info("GET obtener products que sean iguales a la descripción");
//        var prodFiltered = this.products.stream()
//                .filter(product -> product.getTitle().contains(title));
//        return prodFiltered.collect(Collectors.toList());
//    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) throws ApiRestException {
        logger.info("GET obtener producto por el id");

        return this.products.get(this.getProdIndex(id));
    }

    @PostMapping("/products")
    Product createProduct(@RequestBody Product newProduct) {
        logger.info("POST Request recibido");

        newProduct.setId(this.products.get(this.products.size() - 1).getId() + 1);
        this.products.add(newProduct);
        return newProduct;
    }

    @PutMapping("/products/{id}")
    public Product updateProductById(@PathVariable Long id, @RequestBody Product newProd) throws ApiRestException {
        logger.info("PUT Request recibido por id");

        newProd.setId(id);

        this.products.set(this.getProdIndex(id), newProd);

        return newProd;
    }

    @DeleteMapping("/products/{id}")
    public List<Product> updateProductById(@PathVariable Long id) throws ApiRestException {
        logger.info("DELETE Request recibido por id");

        this.products.remove(this.getProdIndex(id));

        return this.products;
    }

    private int getProdIndex (Long id) throws ApiRestException{

        if(id == 0) {
            throw new ApiRestException("El identificador del mensaje debe ser mayor a 0");
        } else if (this.products.size() == 0) {
            throw new ApiRestException("no hay productos cargados");
        }

        Product prod = this.products
                .stream()
                .filter(product -> Objects.equals(product.getId(), id))
                .findFirst()
                .orElseThrow(() -> new ApiRestException("producto no encontrado"));

        return this.products.indexOf(prod);
    }
}
