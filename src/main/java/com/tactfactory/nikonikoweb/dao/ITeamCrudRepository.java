package com.tactfactory.nikonikoweb.dao;

import org.springframework.data.repository.CrudRepository;

import com.tactfactory.nikonikoweb.models.Team;

public interface ITeamCrudRepository extends CrudRepository<Team, Long> {

}
