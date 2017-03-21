package com.tactfactory.nikonikoweb.controllers;

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
import com.tactfactory.nikonikoweb.dao.IProjectCrudRepository;
import com.tactfactory.nikonikoweb.dao.ITeamCrudRepository;
import com.tactfactory.nikonikoweb.models.NikoNiko;
import com.tactfactory.nikonikoweb.models.Project;
import com.tactfactory.nikonikoweb.models.Team;
import com.tactfactory.nikonikoweb.utils.DumpFields;

@Controller
@RequestMapping(ProjectController.BASE_URL)
public class ProjectController extends ViewBaseController<Project> {

	public final static String BASE_URL = "/project";

	public final static String PROJECT_VIEW = "project";

	public final static String index = "index";

	protected final static String teams = "teams";
	protected final static String teamsLinks = "teamslink";

	protected final static String nikonikos = "nikonikos";
	protected final static String nikonikosLinks = "nikonikoslink";

	protected final static String PATH_INDEX = PROJECT_VIEW + PATH + index;

	protected final static String PATH_TEAMS = PROJECT_VIEW + PATH + teams;
	protected final static String PATH_TEAMSLINKS = PROJECT_VIEW + PATH
			+ teamsLinks;
	protected final static String PATH_TEAMSLINKS_REDIRECT = REDIRECT + PATH
			+ PROJECT_VIEW + index;

	protected final static String PATH_NIKONIKOS =  PROJECT_VIEW + PATH
			+ nikonikos;
	protected final static String PATH_NIKONIKOSLINKS = PROJECT_VIEW + PATH
			+ nikonikosLinks;
	protected final static String PATH_NIKONIKOSLINKS_REDIRECT = REDIRECT
			+ PATH + PROJECT_VIEW + index;

	protected final static String PROJECT_ID = "{projectId}";
	protected final static String ROUTE_INDEX = index;

	protected final static String ROUTE_TEAMS = PROJECT_ID + PATH + teams;
	protected final static String ROUTE_TEAMSLINKS = PROJECT_ID + PATH
			+ teamsLinks;

	protected final static String ROUTE_NIKONIKOS = PROJECT_ID + PATH
			+ nikonikos;
	protected final static String ROUTE_NIKONIKOSLINKS = PROJECT_ID + PATH
			+ nikonikosLinks;

	public ProjectController() {
		super(Project.class, BASE_URL);
		this.basePage = index;
	}

	@Autowired
	IProjectCrudRepository projectCrud;

	@Autowired
	ITeamCrudRepository teamCrud;

	@Autowired
	INikoNikoCrudRepository nikonikoCrud;

	@RequestMapping(ROUTE_INDEX)
	public String projects(Model model) {
		model.addAttribute("page", "All projects");
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
	public String setTeamsForProjectGet(Model model,
			@PathVariable Long projectId) {
		Project project = super.getItem(projectId);

		model.addAttribute("page", project.getName() + " teams linker");
		model.addAttribute("fields", Team.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(project));

		List<Team> teams = (List<Team>) teamCrud.findAll();
		model.addAttribute("items", DumpFields.<Team> listFielder(teams));

		ArrayList<Long> teamsIds = new ArrayList<Long>();
		for (Team team : project.getTeams()) {
			teamsIds.add(team.getId());
		}
		model.addAttribute("linkedItems", teamsIds);

		return PATH_TEAMSLINKS;
	}

	@RequestMapping(path = ROUTE_TEAMSLINKS, method = RequestMethod.POST)
	public String setTeamsForProjectPost(Model model,
			@PathVariable Long projectId,
			@RequestParam(value = "ids[]") Long[] ids) {
		Project project = super.getItem(projectId);

		project.getTeams().clear();

		for (Long id : ids) {
			if (id != 0) {
				project.getTeams().add(teamCrud.findOne(id));
			}
		}

		projectCrud.save(project);

		return PATH_TEAMSLINKS_REDIRECT;
	}

	@RequestMapping(path = ROUTE_TEAMS, method = RequestMethod.GET)
	public String getTeamsForProject(Model model, @PathVariable Long projectId) {
		Project project = super.getItem(projectId);

		model.addAttribute("page", project.getName() + " teams");
		model.addAttribute("fields", Team.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(project));
		model.addAttribute("items", DumpFields
				.<Team> listFielder(new ArrayList<Team>(project.getTeams())));
		return PATH_TEAMS;
	}

	@RequestMapping(path = ROUTE_NIKONIKOSLINKS, method = RequestMethod.GET)
	public String setNikoNikosForProjectGet(Model model,
			@PathVariable Long projectId) {
		Project project = super.getItem(projectId);

		model.addAttribute("page", project.getName() + " nikonikos linker");
		model.addAttribute("fields", NikoNiko.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(project));

		List<NikoNiko> nikoNikos = (List<NikoNiko>) nikonikoCrud.findAll();
		model.addAttribute("items",
				DumpFields.<NikoNiko> listFielder(nikoNikos));

		ArrayList<Long> nikoNikosIds = new ArrayList<Long>();
		for (NikoNiko nikoNiko : project.getNikoNikos()) {
			nikoNikosIds.add(nikoNiko.getId());
		}
		model.addAttribute("linkedItems", nikoNikosIds);

		return PATH_NIKONIKOSLINKS;
	}

	@RequestMapping(path = ROUTE_NIKONIKOSLINKS, method = RequestMethod.POST)
	public String setNikoNikosForProjectPost(Model model,
			@PathVariable Long projectId,
			@RequestParam(value = "ids[]") Long[] ids) {
		Project project = super.getItem(projectId);

		project.getNikoNikos().clear();

		for (Long id : ids) {
			if (id != 0) {
				project.getNikoNikos().add(nikonikoCrud.findOne(id));
			}
		}

		projectCrud.save(project);

		return PATH_NIKONIKOSLINKS_REDIRECT;
	}

	@RequestMapping(path = ROUTE_NIKONIKOS, method = RequestMethod.GET)
	public String getNikoNikosForProject(Model model,
			@PathVariable Long projectId) {
		Project project = super.getItem(projectId);

		model.addAttribute("page", project.getName() + " nikonikos");
		model.addAttribute("fields", NikoNiko.FIELDS);
		model.addAttribute("currentItem", DumpFields.fielder(project));
		model.addAttribute("items", DumpFields
				.<NikoNiko> listFielder(new ArrayList<NikoNiko>(project
						.getNikoNikos())));
		return PATH_NIKONIKOS;
	}
}
