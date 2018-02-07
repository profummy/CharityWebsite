package com.fdmgroup.SoloProjectFINAL.controllers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fdmgroup.SoloProjectFINAL.daos.DonorDAOImp;
import com.fdmgroup.SoloProjectFINAL.entities.Donor;

@Controller
public class WelcomeController {

	private Logger logger = Logger.getLogger(getClass());

	public WelcomeController() {
		super();

	}

	@RequestMapping("/")
	public String goToHomepage() {

		logger.trace("Client request to url : /");

		return "views/Homepage";

	}

	@RequestMapping("/loginDonorOrManager")
	public String processLogin(HttpServletRequest request, HttpSession session) {

		logger.info("Login must be valid Donor or Manager otherwise loginFailure");

		session.setAttribute("username", request.getUserPrincipal().getName());
		if (request.isUserInRole("Donor")) {
			return "redirect:donor/DonorHome";
		} else {
			if (request.isUserInRole("Manager")) {
				return "redirect:manager/ManagerHome";
			}
			return "redirect:views/loginFailure";
		}
	}

	@RequestMapping("/donor/DonorHome")
	public String goToDonorHome() {
		logger.trace("Client request to url : /donor/DonorHome");
		return "donor/DonorHome";
	}

	@RequestMapping("/manager/ManagerHome")
	public String goToManagerHome() {
		logger.trace("Client request to url : /manager/ManagerHome");
		return "manager/ManagerHome";
	}

	@RequestMapping("/logout") //
	public String logout(HttpSession session) {

		session.invalidate();
		logger.info("User is logged out");
		return "redirect:/";

	}

	@RequestMapping("/signup")
	public String goToRegister(Model model) {
		logger.trace("Client request to url : /views/Register");
		Donor donor = new Donor();
		model.addAttribute("donor", donor);
		return "views/Register";

	}

	@RequestMapping("/doRegister") // process from the registration page
	public String doRegister(Donor donor, Model model) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DemoPersistence");
		DonorDAOImp donorDao = new DonorDAOImp(factory);

		if (!donor.getPassword().equals(donor.getConfirmpassword())) {
			model.addAttribute("message1", "Passwords do not match");
			return "views/Register";
		}

		try {
			donorDao.addDonor(donor);
			logger.info("new Donor added " + donor);
			model.addAttribute("message", "Signup successful, you can login now");
			return "views/Homepage";
		} catch (PersistenceException e) {
			model.addAttribute("message", "Username already exists");
		}
		return "views/Register";
	}

}
