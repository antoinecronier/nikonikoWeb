package com.tactfactory.nikonikoweb.service.security;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
