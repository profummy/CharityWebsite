package com.fdmgroup.SoloProjectFINAL.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.SoloProjectFINAL.daos.DonorDAOImp;

import com.fdmgroup.SoloProjectFINAL.daos.ProjectDAOImp;
import com.fdmgroup.SoloProjectFINAL.entities.Donation;
import com.fdmgroup.SoloProjectFINAL.entities.Donor;
import com.fdmgroup.SoloProjectFINAL.entities.Project;

@Controller
public class DonorController {

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private DonorDAOImp donorDao;

	@Autowired
	private ProjectDAOImp projectDao;

	public DonorController() {
		super();

	}

	public DonorController(DonorDAOImp donorDao) {
		super();
		this.donorDao = donorDao;
	}

	@RequestMapping("/donor/donorHome")
	public String goToDonorHome() {
		logger.trace("Client request to url : /donor/donorHome");
		return "donor/DonorHome";

	}

	@RequestMapping("/donor/donorhome")
	public String goToDonorHome(@RequestParam String username, HttpSession session) {
		session.setAttribute("username", username);
		return "donor/DonorHome";

	}

	@RequestMapping("/donor/logout") //
	public String logout(HttpSession session) {
		session.invalidate();
		logger.trace("Client is logged out");
		return "redirect:/";

	}

	@RequestMapping("/donor/showProfile")
	public String goToShowProfile(Model model, String username) {
		logger.trace("Client request to url : /donor/showProfile");
		Donor donorInfo = donorDao.getDonor(username);
		model.addAttribute("donorInfo", donorInfo);
		return "donor/ShowProfile";
	}

	@RequestMapping("/donor/processShowProfile")
	public String processShowProfile(Model model, String username) {
		donorDao.getDonor(username);
		logger.info("Donor information displayed");
		return "donor/DonorHome";
	}

	@RequestMapping("/donor/editprofile")
	public String goToEditProfilepage(Donor donor, Model model) {
		logger.trace("Client request to url : /donor/editProfile");
		donorDao.updateDonor(donor);

		model.addAttribute("message", "Details successfully updated");

		return "donor/EditProfile";

	}

	// @RequestMapping("/donor/EditProfile")
	// public String editProfile(Model model, HttpSession session){
	// String username = (String)session.getAttribute("username");
	//
	// Donor donor = donorDao.getDonor(username);
	// donor.setRole(Donor);
	// model.addAttribute("donor",donor);
	// return "donor/EditProfile";
	// }

	// @RequestMapping("/donor/UpdateProfile")
	// public String updateProfile(Donor donor, Model model){
	// donor.setRole("Donor");
	// customerDAO.updateCustomer(customer);
	// model.addAttribute("customer",customer);
	// model.addAttribute("message", "Edit succesfully");
	// return "donor/EditProfile";
	// }

	@RequestMapping("/donor/showProjects")
	public String goToShowProjects(Model model) {
		logger.trace("Client request to url : /donor/showProjects");
		List<Project> projectList = donorDao.getAllProjects();
		model.addAttribute("projectList", projectList);
		return "donor/ShowProjects";
	}

	@RequestMapping("/donor/processShowProjects")
	public String processShowProjects(Model model) {
		donorDao.getAllProjects();
		logger.info("All projects information displayed");
		model.addAttribute("message8", "List of projects found");
		return "donor/DonorHome";
	}

	@RequestMapping("/donor/addDonation")
	public String goToAddDonation(Model model) {
		logger.trace("Client request to url : /donor/addDonation");
		Donation donation = new Donation();
		model.addAttribute("donation", donation);
		return "donor/AddDonation";

	}

	@RequestMapping("/donor/processAddDonation")
	public String processAddDonation(@RequestParam int id, @RequestParam double amount, Model model) {

		Project project = projectDao.getProject(id);

		Donation donation = new Donation();
		donation.setAmount(amount);

		project.addDonation(donation);
		logger.info("New donation added");
		projectDao.updateProject(project);
		logger.info("Project updated with donation amount");
		model.addAttribute("messageD", "Donation added successfully. Thank you for your donation!");
		return "donor/DonorHome";

	}

	@RequestMapping("/donor/removeProfile")
	public String goToRemoveProfile(Model model) {
		logger.trace("Client request to url : /donor/removeProfile");
		Donor donor = new Donor();
		model.addAttribute("donor", donor);
		return "donor/RemoveProfile";

	}

	@RequestMapping("/donor/processRemoveProfile")
	public String processRemoveProfile(Model model, Principal principal, HttpSession session) {

		donorDao.removeDonor(principal.getName());
		logger.info("Current user profile is removed");
		session.invalidate();
		logger.info("User is logged out after profile removed");
		model.addAttribute("message2", "Your profile has been successfully deleted.");
		return "redirect:/";
	}

}
