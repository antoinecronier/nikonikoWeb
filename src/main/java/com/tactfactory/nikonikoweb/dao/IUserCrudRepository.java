package com.tactfactory.nikonikoweb.dao;

import org.springframework.data.repository.CrudRepository;

import com.tactfactory.nikonikoweb.models.User;

public interface IUserCrudRepository extends CrudRepository<User, Long> {

}
