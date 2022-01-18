package com.coderhouse.service.service;

import com.coderhouse.service.annotations.ClientAnnotation;
import com.coderhouse.service.handle.ApiRestException;
import com.coderhouse.service.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ClientServiceImpl implements ClientService{

    private static final Logger logger = LogManager.getLogger(ConfigSubject.class);

    private List<Client> clients = new ArrayList<Client>() {
        {
            add(new Client(1L, "Daniel", "Vinet"));
            add(new Client(2L, "Lucas", "Vinet"));
        }
    };

    public List<Client> getClients() {

        logger.info("getClients");
        return clients;
    }

    @Override
    @ClientAnnotation
    public void update(Long id, Client updatedClient) throws ApiRestException {
        logger.info("updating");

        if (Objects.isNull(updatedClient.getNombre()) || updatedClient.getNombre() == "") {
            throw new ApiRestException("campo Nombre no puede ser null");
        }

        clients.set(this.getClientIndex(id), updatedClient);
    }

    @Override
    @ClientAnnotation
    public void delete (Long id) throws ApiRestException{
        logger.info("deleting");
        clients.remove(this.getClientIndex(id));
    }

    private int getClientIndex (Long id) throws ApiRestException {

        if(id == 0) {
            throw new ApiRestException("El identificador del cliente debe ser mayor a 0");
        } else if (this.clients.size() == 0) {
            throw new ApiRestException("no hay clientes cargados");
        }

        Client client = this.clients
                .stream()
                .filter(cl -> Objects.equals(cl.getId(), id))
                .findFirst()
                .orElseThrow(() -> new ApiRestException("cliente no encontrado"));

        return this.clients.indexOf(client);
    }
}
