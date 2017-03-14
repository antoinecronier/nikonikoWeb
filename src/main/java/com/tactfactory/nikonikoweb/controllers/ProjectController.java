package com.tactfactory.nikonikoweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tactfactory.nikonikoweb.dao.IProjectCrudRepository;
import com.tactfactory.nikonikoweb.models.Project;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@RequestMapping(path="/", method = RequestMethod.GET)
	public String index(){

		return "project/index";
	}

	@Autowired
	private IProjectCrudRepository projectCrud;
}
