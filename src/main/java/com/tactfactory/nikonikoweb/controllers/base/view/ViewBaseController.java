package com.tactfactory.nikonikoweb.controllers.base.view;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tactfactory.nikonikoweb.controllers.base.BaseController;
import com.tactfactory.nikonikoweb.models.base.DatabaseItem;
import com.tactfactory.nikonikoweb.utils.DumpFields;

public abstract class ViewBaseController<T extends DatabaseItem> extends
		BaseController<T> {

	private String baseName;

	protected String listView;
	protected String baseView;

	protected ViewBaseController(Class<T> clazz) {
		super(clazz);

		this.baseName = this.getClazz().getSimpleName().toUpperCase();
		this.baseView = "base";

		this.listView = this.baseView + PATH_LIST_FILE;
	}

	@RequestMapping(value = { PATH, ROUTE_LIST }, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("page", this.baseName + " " + LIST_ACTION);
		model.addAttribute("fields",
				DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("items", DumpFields.listFielder(super.getItems()));
		return listView;
	}
}
