package com.tactfactory.nikonikoweb.controllers.view.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tactfactory.nikonikoweb.controllers.base.view.ViewBaseController;
import com.tactfactory.nikonikoweb.dao.INikoNikoCrudRepository;
import com.tactfactory.nikonikoweb.dao.ITeamCrudRepository;
import com.tactfactory.nikonikoweb.dao.IUserCrudRepository;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.models.Team;
import com.tactfactory.nikonikoweb.models.User;
import com.tactfactory.nikonikoweb.utils.DumpFields;

@Controller
@RequestMapping(UserViewController.BASE_URL)
public class UserViewController extends ViewBaseController<User> {

	public final static String BASE_URL = "/admin/user";

	public final static String ROUTE_BASE = "user";
	public final static String PATH_BASE = "base";

	public final static String index = "index";

	protected final static String teams = "teams";
	protected final static String teamsLinks = "teamslink";

	protected final static String nikonikos = "nikonikos";
	protected final static String nikonikosLinks = "nikonikoslink";

	protected final static String associationMultiShow = "associationMutliShow";
	protected final static String associationMultiEdit = "associationMultiEdit";

	protected final static String PATH_INDEX = PATH_BASE + PATH + index;

	protected final static String PATH_TEAMS = PATH_BASE + PATH
			+ associationMultiShow;
	protected final static String PATH_TEAMSLINKS = PATH_BASE + PATH
			+ associationMultiEdit;
	protected final static String PATH_TEAMSLINKS_REDIRECT = REDIRECT + PATH
			+ PATH_BASE + PATH + index;

	protected final static String PATH_NIKONIKOS = PATH_BASE + PATH
			+ associationMultiShow;
	protected final static String PATH_NIKONIKOSLINKS = PATH_BASE + PATH
			+ associationMultiEdit;
	protected final static String PATH_NIKONIKOSLINKS_REDIRECT = REDIRECT
			+ PATH + PATH_BASE + PATH + index;

	protected final static String PROJECT_ID = "{projectId}";
	protected final static String ROUTE_INDEX = index;

	protected final static String ROUTE_TEAMS = PROJECT_ID + PATH + teams;
	protected final static String ROUTE_TEAMSLINKS = PROJECT_ID + PATH
			+ teamsLinks;

	protected final static String ROUTE_NIKONIKOS = PROJECT_ID + PATH
			+ nikonikos;
	protected final static String ROUTE_NIKONIKOSLINKS = PROJECT_ID + PATH
			+ nikonikosLinks;

	public UserViewController() {
		super(User.class, BASE_URL);
		this.basePage = index;
		this.createRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.deleteRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.updateRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.showRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
		this.listRedirect = REDIRECT + this.basePath + PATH + ROUTE_INDEX;
	}

	@Autowired
	IUserCrudRepository userCrud;

	@Autowired
	ITeamCrudRepository teamCrud;

	@Autowired
	INikoNikoCrudRepository nikonikoCrud;

	@RequestMapping(path = ROUTE_INDEX, method = RequestMethod.GET)
	public String projects(Model model) {
		model.addAttribute("page", "All users");
		model.addAttribute("fields",
				DumpFields.createContentsEmpty(super.getClazz()).fields);
		model.addAttribute("items", DumpFields.listFielder(super.getItems()));
		model.addAttribute(
				"currentItem",
				DumpFields.fielderAdvance(
						DumpFields.createContentsEmpty(super.getClazz()),
						super.getClazz()));
		return PATH_INDEX;
	}

	@RequestMapping(path = ROUTE_TEAMSLINKS, method = RequestMethod.GET)
	public String setTeamsForUserGet(Model model, @PathVariable Long projectId) {
		User user = super.getItem(projectId);

		model.addAttribute("page",
				user.getLastname() + " " + user.getFirstname()
						+ " teams linker");
		model.addAttribute("fields", Team.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(user));

		List<Team> teams = (List<Team>) teamCrud.findAll();
		model.addAttribute("items", DumpFields.<Team> listFielder(teams));

		ArrayList<Long> teamsIds = new ArrayList<Long>();
		for (Team team : user.getTeams()) {
			teamsIds.add(team.getId());
		}
		model.addAttribute("linkedItems", teamsIds);

		return PATH_TEAMSLINKS;
	}

	@RequestMapping(path = ROUTE_TEAMSLINKS, method = RequestMethod.POST)
	public String setTeamsForProjectPost(Model model,
			@PathVariable Long projectId,
			@RequestParam(value = "ids[]") Long[] ids) {
		User user = super.getItem(projectId);

		user.getTeams().clear();

		for (Long id : ids) {
			if (id != 0) {
				user.getTeams().add(teamCrud.findOne(id));
			}
		}

		userCrud.save(user);

		return PATH_TEAMSLINKS_REDIRECT;
	}

	@RequestMapping(path = ROUTE_TEAMS, method = RequestMethod.GET)
	public String getTeamsForProject(Model model, @PathVariable Long projectId) {
		User user = super.getItem(projectId);

		model.addAttribute("page",
				user.getLastname() + " " + user.getFirstname() + " teams");
		model.addAttribute("fields", Team.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(user));
		model.addAttribute("items", DumpFields
				.<Team> listFielder(new ArrayList<Team>(user.getTeams())));
		return PATH_TEAMS;
	}

	@RequestMapping(path = ROUTE_NIKONIKOSLINKS, method = RequestMethod.GET)
	public String setNikoNikosForProjectGet(Model model,
			@PathVariable Long projectId) {
		User user = super.getItem(projectId);

		model.addAttribute("page",
				user.getLastname() + " " + user.getFirstname()
						+ " nikonikos linker");
		model.addAttribute("fields", NikoNiko.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(user));

		List<NikoNiko> nikoNikos = (List<NikoNiko>) nikonikoCrud.findAll();
		model.addAttribute("items",
				DumpFields.<NikoNiko> listFielder(nikoNikos));

		ArrayList<Long> nikoNikosIds = new ArrayList<Long>();
		for (NikoNiko nikoNiko : user.getNikoNikos()) {
			nikoNikosIds.add(nikoNiko.getId());
		}
		model.addAttribute("linkedItems", nikoNikosIds);

		return PATH_NIKONIKOSLINKS;
	}

	@RequestMapping(path = ROUTE_NIKONIKOSLINKS, method = RequestMethod.POST)
	public String setNikoNikosForProjectPost(Model model,
			@PathVariable Long projectId,
			@RequestParam(value = "ids[]") Long[] ids) {
		User user = super.getItem(projectId);

		user.getNikoNikos().clear();

		for (Long id : ids) {
			if (id != 0) {
				user.getNikoNikos().add(nikonikoCrud.findOne(id));
			}
		}

		userCrud.save(user);

		return PATH_NIKONIKOSLINKS_REDIRECT;
	}

	@RequestMapping(path = ROUTE_NIKONIKOS, method = RequestMethod.GET)
	public String getNikoNikosForProject(Model model,
			@PathVariable Long projectId) {
		User user = super.getItem(projectId);

		model.addAttribute("page",
				user.getLastname() + " " + user.getFirstname() + " nikonikos");
		model.addAttribute("fields", NikoNiko.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(user));
		model.addAttribute("items", DumpFields
				.<NikoNiko> listFielder(new ArrayList<NikoNiko>(user
						.getNikoNikos())));
		return PATH_NIKONIKOS;
	}
}
