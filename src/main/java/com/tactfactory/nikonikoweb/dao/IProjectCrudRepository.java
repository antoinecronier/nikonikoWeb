package com.tactfactory.nikonikoweb.dao;

import org.springframework.data.repository.CrudRepository;

import com.tactfactory.nikonikoweb.models.Project;

public interface IProjectCrudRepository extends CrudRepository<Project, Long> {

}
