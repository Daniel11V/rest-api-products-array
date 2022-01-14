package com.coderhouse.service.controller;

import com.coderhouse.service.domain.UserConfig;
import com.coderhouse.service.service.ConfigService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    private static final Logger logger = LogManager.getLogger(ConfigController.class);

    @Autowired
    private ConfigService configService;

    @PutMapping("/users")
    public UserConfig updateConfig(@RequestBody UserConfig newUserConfig) {
        logger.info("PUT Request recibido");
        configService.updateUserConfig(newUserConfig);
        return newUserConfig;
    }
}
