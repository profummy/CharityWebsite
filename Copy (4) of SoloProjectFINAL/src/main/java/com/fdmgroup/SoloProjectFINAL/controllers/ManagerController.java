package com.fdmgroup.SoloProjectFINAL.controllers;

import java.util.List;



import javax.persistence.PersistenceException;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.fdmgroup.SoloProjectFINAL.daos.ManagerDAOImp;
import com.fdmgroup.SoloProjectFINAL.daos.ProjectDAOImp;
import com.fdmgroup.SoloProjectFINAL.entities.Donation;
import com.fdmgroup.SoloProjectFINAL.entities.Donor;
import com.fdmgroup.SoloProjectFINAL.entities.Manager;
import com.fdmgroup.SoloProjectFINAL.entities.Project;

@Controller
public class ManagerController {

	private Logger logger = Logger.getLogger(getClass());
	
	
	@Autowired
	private ManagerDAOImp managerDao;

	
	
	public ManagerController() {
		super();
		
	}


	
	public ManagerController(ManagerDAOImp managerDao) {
		super();
		this.managerDao = managerDao;
	}




	@Autowired
	private ProjectDAOImp projectDao;

	@RequestMapping("/manager/managerHome")
	public String goToManagerHome() {
		
		logger.trace("Client request to url : /manager/managerHome");
		return "manager/ManagerHome";

	}

	@RequestMapping("/manager/managerhome")
	public String goToManagerHome(@RequestParam String username, HttpSession session) {
		session.setAttribute("username", username);
		return "manager/ManagerHome";

	}

	@RequestMapping("/manager/logout") //
	public String logout(HttpSession session) {
		
		session.invalidate();
		logger.info("Manager is logged out");
		return "redirect:/";

	}

	@RequestMapping("/manager/addManager")
	public String goToAddManager(Model model) {
		
		logger.trace("Client request to url : /manager/addManager");
		Manager manager = new Manager();
		model.addAttribute("manager", manager);

		return "manager/AddManager";

	}

	@RequestMapping("/manager/processAddManager")
	public String processAddManager(Manager manager, Model model) {
		
		try {
			managerDao.addManager(manager);
			logger.info("New Manager added " + manager);
			model.addAttribute("message", "Manager added successfully");
			return "manager/ManagerHome";
		} catch (PersistenceException e) {
			model.addAttribute("message", "Username already exists");
		}
		return "manager/AddManager";
	}

	@RequestMapping("/manager/removeManager")
	public String goToRemoveManager(Model model) {
		logger.trace("Client request to url : /manager/removeManager");
		Manager manager = new Manager();
		model.addAttribute("manager", manager);
		return "manager/RemoveManager";

	}

	@RequestMapping("/manager/processRemoveManager")
	public String processRemoveManager(Manager manager, Model model) {

		managerDao.removeManager(manager.getUsername());
		logger.info("Manager is removed " + manager);
		model.addAttribute("message2", "Manager removed successfully");
		return "manager/ManagerHome";
	}

	@RequestMapping("/manager/showManagers")
	public String goToShowManagers(Model model) {
		logger.trace("Client request to url : /manager/showManagers");
		List<Manager> managerList = managerDao.getAllManagers();
		model.addAttribute("managerList", managerList);
		return "manager/ShowManagers";

	}

	@RequestMapping("/manager/processShowManagers")
	public String processShowManagers(Manager manager, Model model) {

		managerDao.getAllManagers();
		logger.info("List of Managers found");
		model.addAttribute("message4", "List of managers found");
		return "manager/ManagerHome";
	}

	@RequestMapping("/manager/searchDonor")
	public String goToSearchDonor(Model model) {
		logger.trace("Client request to url : /manager/searchDonor");
		return "manager/SearchDonor";

	}

	@RequestMapping("/manager/processSearchDonor")
	public String processShowDonor(@RequestParam String donorName, Model model) {

		Donor donor = managerDao.getDonor(donorName);
		logger.info("Donor information displayed");
		model.addAttribute("message5", "Donor information found");
		model.addAttribute("donor", donor);
		return "manager/ShowDonor";
	}

	@RequestMapping("/manager/showDonor")
	public String goToShowDonor(Model model, String username) {
		logger.trace("Client request to url : /manager/showDonor");
		Donor donor = managerDao.getDonor(username);

		if (donor == null) {
			model.addAttribute("errorMessage", "Donor with username " + username + "does not exist");
		}

		model.addAttribute("username", username);
		return "manager/showDonor";

	}

	@RequestMapping("/manager/showDonors")
	public String goToShowDonors(Model model) {
		logger.trace("Client request to url : /manager/showDonors");
		List<Donor> donorList = managerDao.getAllDonors();
		model.addAttribute("donorList", donorList);
		return "manager/ShowDonors";

	}

	@RequestMapping("/manager/processShowDonors")
	public String processShowDonors(Model model) {

		managerDao.getAllDonors();
		logger.info("All Donors information displayed");
		model.addAttribute("message6", "List of donors found");
		return "manager/ManagerHome";
	}

	@RequestMapping("/manager/showDonations")
	public String goToShowDonations(Model model) {
		logger.trace("Client request to url : /manager/showDonations");
		List<Donation> donationList = managerDao.getAllDonations();
		model.addAttribute("donationList", donationList);
		return "manager/ShowDonations";

	}

	@RequestMapping("/manager/processShowDonations")
	public String processShowDonations(Model model) {

		managerDao.getAllDonations();
		logger.info("All Donation information displayed");
		model.addAttribute("message7", "List of donations found");
		return "manager/ManagerHome";
	}

	@RequestMapping("/manager/manageProjects")
	public String goToManageProjects(Model model) {
		logger.trace("Client request to url : /manager/manageProjects");
		List<Project> projectList = managerDao.getAllProjects();
		model.addAttribute("projectList", projectList);
		return "manager/ManageProjects";

	}

	@RequestMapping("/manager/processManageProjects")
	public String goToUpdateProject(@RequestParam int id, Model model) {
		Project project = projectDao.getProject(id);
		System.out.println("processUpdateProject " + project.getId());
		managerDao.getAllProjects();
		model.addAttribute("project", project);
		return "manager/UpdateProject";
	}

	@RequestMapping("/manager/processUpdateProject")
	public String processUpdateProject(Project project, Model model) {

		System.out.println("Update project " + project.getId());
		projectDao.updateProject(project);
		logger.info("Project updated");
		model.addAttribute("project", project);
		model.addAttribute("message", "Project updated successfully.");
		return "manager/UpdateProject";
	}

	@RequestMapping("/manager/addProject")
	public String goToAddProject(Model model) {
		logger.trace("Client request to url : /manager/addProject");
		Project project = new Project();
		model.addAttribute("project", project);
		return "manager/AddProject";

	}

	@RequestMapping("/manager/processAddProject")
	public String processAddProject(Project project, Model model) {

		if (project.getName() == null || project.getName() == "" || project.getLocation() == null
				|| project.getLocation() == "") {
			model.addAttribute("message11", "Please enter a project name and location");
			return "manager/AddProject";
		} else {
			Project projectFromDB = managerDao.getProjectsByNameAndLocation(project.getName(), project.getLocation());
			if (projectFromDB != null) {
				model.addAttribute("message12", "Project already exists");
				return "manager/AddProject";
			} else {
				managerDao.addProject(project);
				logger.info("New Project added " + project);
				model.addAttribute("message9", "Project added successfully");
				return "manager/ManagerHome";
			}
		}
	}

	@RequestMapping("/manager/removeProject")
	public String goToRemoveProject(Model model) {
		logger.trace("Client request to url : /manager/removeProject");
		Project project = new Project();
		model.addAttribute("project", project);
		return "manager/RemoveProject";

	}

	@RequestMapping("/manager/processRemoveProject")
	public String processRemoveProject(Project project, Model model) {

		try {
			managerDao.removeProject(project.getId());
			logger.info("Project removed");
			model.addAttribute("message10", "Project removed successfully");
			return "manager/ManagerHome";
		} catch (IllegalArgumentException e) {
			logger.info("IllegalArgumentException likely if project Id is 0");
			model.addAttribute("message", "Project id invalid");
		}
		return "manager/RemoveProject";
	}

}
