package com.coderhouse.service.service;

import com.coderhouse.service.handle.ApiRestException;
import com.coderhouse.service.model.Client;

public interface ClientService {

    public void update(Long id, Client client) throws ApiRestException;

    public void delete(Long id) throws ApiRestException;
}
