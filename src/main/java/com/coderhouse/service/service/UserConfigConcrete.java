package com.coderhouse.service.service;

import com.coderhouse.service.utils.ConfigObserver;
import com.coderhouse.service.utils.ConfigType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserConfigConcrete implements InitializingBean, ConfigObserver {

    private static final Logger logger = LogManager.getLogger(UserConfigConcrete.class);

    @Autowired
    ConfigSubject configSubject;

    @Autowired
    ConfigService configService;

    private int id;
    private String rol;
    private String email;
    private String phone;

    @Override
    public void updateConfig(Object event) {
        logger.info("Actualiza el config");
        if (event.equals(ConfigType.USER)) {
            this.rol = configService.getUserRol();
            logger.info("Rol nuevo {}", this.rol);

            this.email = configService.getUserEmail();
            logger.info("Email nuevo {}", this.email);

            this.phone = configService.getUserPhone();
            logger.info("Phone nuevo {}", this.phone);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        configSubject.addObserver(this);
    }

}
