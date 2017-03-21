package com.tactfactory.nikonikoweb.controllers;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tactfactory.nikonikoweb.utils.DumpFields;

@Controller
@RequestMapping(ApplicationController.BASE_URL)
public class ApplicationController {

	public final static String PATH = "/";

	public final static String BASE_URL = "/";

	public final static String ROUTE_BASE = "admin";
	public final static String PATH_BASE = "application" + PATH + "admin";

	public static final String PATH_ADMIN_INDEX = PATH_BASE + PATH + "index";
	public static final String ROUTE_ADMIN = ROUTE_BASE;

	public static final String VIEW_CONTROLLER = "com.tactfactory.nikonikoweb.controllers.view.admin";
	public static final String TRUNCATE_PATTERN = "ViewController";
	public static final String ROUTE_INDEX = "/index";

	@RequestMapping(path = ROUTE_ADMIN, method = RequestMethod.GET)
	public String welcome(Model model) {
		ArrayList<String> controllers = new ArrayList<String>();

		try {
			controllers = DumpFields.getClassesNames(VIEW_CONTROLLER);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ArrayList<String> controllersBaseName = new ArrayList<String>();
		for (String string : controllers) {
			controllersBaseName.add(string.replace(TRUNCATE_PATTERN, "").toLowerCase());
		}

		model.addAttribute("page", "Admin");
		model.addAttribute("pathlinker",ROUTE_BASE + PATH);
		model.addAttribute("pathindex",ROUTE_INDEX);
		model.addAttribute("controllers", controllersBaseName);

		return PATH_ADMIN_INDEX;
	}
}
