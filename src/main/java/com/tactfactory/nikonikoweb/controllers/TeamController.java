package com.tactfactory.nikonikoweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tactfactory.nikonikoweb.dao.ITeamCrudRepository;
import com.tactfactory.nikonikoweb.models.Team;

@Controller
@RequestMapping("/team")
public class TeamController {

	@RequestMapping(path="/", method = RequestMethod.GET)
	public String index(){

		return "team/index";
	}

	@Autowired
	private ITeamCrudRepository teamCrud;
}
