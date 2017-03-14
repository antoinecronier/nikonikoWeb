package com.tactfactory.nikonikoweb.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tactfactory.nikonikoweb.dao.INikoNikoCrudRepository;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.utils.DumpFields;

@Controller
@RequestMapping("/nikoniko")
public class NikoNikoController {

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("fields",NikoNiko.FIELDS);

		model.addAttribute("items", DumpFields
				.listFielder((List<NikoNiko>) nikoCrud.findAll()));

		return "nikoniko/index";
	}

	@Autowired
	private INikoNikoCrudRepository nikoCrud;
}
