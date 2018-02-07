package com.fdmgroup.SoloProjectFINAL;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.SoloProjectFINAL.controllers.DonorController;
import com.fdmgroup.SoloProjectFINAL.controllers.ManagerController;
import com.fdmgroup.SoloProjectFINAL.daos.DonorDAOImp;
import com.fdmgroup.SoloProjectFINAL.daos.ManagerDAOImp;
import com.fdmgroup.SoloProjectFINAL.daos.ProjectDAOImp;
import com.fdmgroup.SoloProjectFINAL.entities.Donation;
import com.fdmgroup.SoloProjectFINAL.entities.Donor;
import com.fdmgroup.SoloProjectFINAL.entities.Manager;
import com.fdmgroup.SoloProjectFINAL.entities.Project;

public class DonorControllerTest {

	private ProjectDAOImp pDao;
	private DonorDAOImp dDao;
	private DonorController dController;
	private Model model;
	private Principal principal;
	private HttpSession session;
	private List<Manager> mlist;
	private List<Donor> dlist;
	private List<Donation> dolist;
	private List<Project> plist;
	private String username;
	private Donor donor;
	private Project project;
	private int id;
	private double amount;

	@Before
	public void setUp() {
		pDao = mock(ProjectDAOImp.class);
		dDao = mock(DonorDAOImp.class);
		model = mock(Model.class);
		session = mock(HttpSession.class);
		principal = mock(Principal.class);
		dController = new DonorController(dDao);
		project = mock(Project.class);

	}

	@Test
	public void testGoToDonorHome() {

		String pageName = dController.goToDonorHome();

		assertEquals("donor/DonorHome", pageName);

	}

	@Test
	public void testGoToDonorHomeAndSetsAttributeUsername() {

		String pageName = dController.goToDonorHome("username", session);
		assertEquals("donor/DonorHome", pageName);

	}

	@Test
	public void testGoToIndexAndDonorLogout() {

		String pageName = dController.logout(session);

		verify(session).invalidate();
		assertEquals("redirect:/", pageName);

	}

	@Test
	public void testGoToEditProfile() {

		String pageName = dController.goToEditProfilepage(donor, model);

		assertEquals("donor/EditProfile", pageName);

	}

	@Test
	public void testgoToRemoveProfile() {

		String pageName = dController.goToRemoveProfile(model);
		verify(model).addAttribute(anyString(), anyObject());
		assertEquals("donor/RemoveProfile", pageName);

	}

	@Test
	public void testProcessRemoveProfileAndReturnsToIndex() {

		// Act - Method you want to test
		Donor donor = new Donor();
		String pageName = dController.processRemoveProfile(model, principal, session);
		// Assert
		verify(dDao).removeDonor(principal.getName());
		verify(session).invalidate();
		verify(model).addAttribute(anyString(), anyString());
		assertEquals("redirect:/", pageName);
	}

	@Test
	public void testgoToShowProfile() {
		String pageName = dController.goToShowProfile(model, username);
		verify(model).addAttribute(anyString(), anyObject());
		assertEquals("donor/ShowProfile", pageName);

	}

	@Test
	public void testProcessShowProfileAndReturnsHome() {

		// Act - Method you want to test
		Donor donor = new Donor();
		String pageName = dController.processShowProfile(model, username);
		// Assert
		verify(dDao).getDonor(username);
		assertEquals("donor/DonorHome", pageName);
	}

	@Test
	public void testgoToShowProjects() {

		plist = dDao.getAllProjects();
		String pageName = dController.goToShowProjects(model);
		verify(model).addAttribute(anyString(), anyObject());
		assertEquals("donor/ShowProjects", pageName);

	}

	@Test
	public void testProcessShowProjectsAndReturnsHome() {

		// Act - Method you want to test
		Project project = new Project();
		String pageName = dController.processShowProjects(model);
		// Assert
		verify(dDao).getAllProjects();
		verify(model).addAttribute(anyString(), anyString());
		assertEquals("donor/DonorHome", pageName);
	}

	@Test
	public void testgoToAddDonation() {

		String pageName = dController.goToAddDonation(model);
		verify(model).addAttribute(anyString(), anyObject());
		assertEquals("donor/AddDonation", pageName);

	}

	@Test
	public void testProcessAddDonation() {

		Donation donation = new Donation();
		Project project = new Project();
		project = pDao.getProject(id);
		String pageName = dController.processAddDonation(id, amount, model);
		verify(dDao).addDonation(donation);
		verify(model).addAttribute(anyString(), anyString());
		assertEquals("donor/DonorHome", pageName);
	}

}
