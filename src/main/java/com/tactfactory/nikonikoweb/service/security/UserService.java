package com.tactfactory.nikonikoweb.service.security;

import com.tactfactory.nikonikoweb.models.User;

public interface UserService {
    void save(User user);

    User findByLogin(String login);
}
