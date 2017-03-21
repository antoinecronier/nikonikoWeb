package com.tactfactory.nikonikoweb.controllers.view.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tactfactory.nikonikoweb.controllers.base.view.ViewBaseController;
import com.tactfactory.nikonikoweb.models.NikoNiko;

@Controller
@RequestMapping(NikoNikoViewController.BASE_URL)
public class NikoNikoViewController extends ViewBaseController<NikoNiko>{

	public final static String BASE_URL = "/admin/nikoniko";

	public NikoNikoViewController() {
		super(NikoNiko.class, BASE_URL);
	}

	@RequestMapping("index")
	public String nikonikoCreate(){
		return "";
	}
}
