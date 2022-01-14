package com.coderhouse.service.service;

import com.coderhouse.service.utils.ConfigObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class ConfigSubject {

    private static final Logger logger = LogManager.getLogger(ConfigSubject.class);

    private final Set<ConfigObserver> observers;

    public ConfigSubject() { observers = Collections.synchronizedSet(new HashSet<>()); }

    public void addObserver(ConfigObserver observer) {
        logger.info("Se agrega un observador");
        observers.add(observer);
    }

    public void removeObserver(ConfigObserver observer) {
        logger.info("Se elimina un observador");
        observers.remove(observer);
    }

    public void notifyObservers(Object event) {
        synchronized (observers) {
            logger.info("Se notifica a los observadores");
            observers.forEach(observer -> observer.updateConfig(event));
        }
    }
}
