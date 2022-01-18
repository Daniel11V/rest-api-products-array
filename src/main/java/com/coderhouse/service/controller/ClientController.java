package com.coderhouse.service.controller;

import com.coderhouse.service.handle.ApiRestException;
import com.coderhouse.service.model.Client;
import com.coderhouse.service.service.ClientServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    Logger logger = LogManager.getLogger(ClientController.class);

    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping("/clients")
    public List<Client> getClients() throws ApiRestException{

        logger.info("GET Request recibido string");

        if (clientService.getClients().size() == 0) {
            throw new ApiRestException("no hay clientes cargados");
        }

        return clientService.getClients();
    }

    @PutMapping("/clients/{id}")
    public List<Client> updateClients(@PathVariable Long id, @RequestBody Client updatedClient) throws ApiRestException{

        logger.info("PUT Request recibido string");

        if (clientService.getClients().size() == 0) {
            throw new ApiRestException("no hay clientes cargados");
        }

        clientService.update(id, updatedClient);
        return clientService.getClients();
    }

    @DeleteMapping("/clients/{id}")
    public List<Client> celeteClients(@PathVariable Long id) throws ApiRestException{

        logger.info("GET Request recibido string");

        if (clientService.getClients().size() == 0) {
            throw new ApiRestException("no hay clientes cargados");
        }
        clientService.delete(id);
        return clientService.getClients();
    }

}
