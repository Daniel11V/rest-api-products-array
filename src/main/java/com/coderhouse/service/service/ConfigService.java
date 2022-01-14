package com.coderhouse.service.service;

import com.coderhouse.service.domain.UserConfig;
import com.coderhouse.service.utils.ConfigType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    @Autowired
    ConfigSubject configSubject;

    @Value(value = "${user.test.rol}")
    private String userRol;

    @Value(value = "${user.test.email}")
    private String userEmail;

    @Value(value = "${user.test.phone}")
    private String userPhone;

    public void updateUserConfig (UserConfig userParam) {
        userRol = userParam.getRol();
        userEmail = userParam.getEmail();
        userPhone = userParam.getPhone();
        configSubject.notifyObservers(ConfigType.USER);
    }

    public String getUserRol() { return userRol; }
    public String getUserEmail() { return userEmail; }
    public String getUserPhone() { return userPhone; }

}
