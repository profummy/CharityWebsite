package com.fdmgroup.SoloProjectFINAL;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.fdmgroup.SoloProjectFINAL.controllers.WelcomeController;
import com.fdmgroup.SoloProjectFINAL.daos.DonorDAOImp;
import com.fdmgroup.SoloProjectFINAL.entities.Donor;

public class WelcomeControllerTest {

	private WelcomeController wController;
	private Model model;
	private DonorDAOImp dDao;
	private HttpSession session;
	private HttpServletRequest request;
	private String username;
	private Donor donor;
	private EntityManagerFactory factory;
	private EntityManager emanager;
	private EntityTransaction transaction;

	@Before
	public void setUp() {

		model = mock(Model.class);
		session = mock(HttpSession.class);
		dDao = mock(DonorDAOImp.class);
		request = mock(HttpServletRequest.class);
		wController = new WelcomeController();
		donor=mock(Donor.class);
		factory = mock(EntityManagerFactory.class);
		emanager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		when(factory.createEntityManager()).thenReturn(emanager); 
		when(emanager.getTransaction()).thenReturn(transaction);

	}

	@Test
	public void testGoToHomepage() {

		String pageName = wController.goToHomepage();

		assertEquals("views/Homepage", pageName);

	}

	@Test
	public void testProcessLoginDonorReturnsToDonorHome() {

		String pageName = wController.processLogin(request, session);
		verify(session).setAttribute("username", request.getUserPrincipal().getName());
		verify(request).isUserInRole(username);
		assertEquals("redirect:donor/DonorHome", pageName);
	}

	@Test
	public void testGoToDonorHome() {

		String pageName = wController.goToDonorHome();

		assertEquals("donor/DonorHome", pageName);

	}

	@Test
	public void testGoToManagerHome() {

		String pageName = wController.goToManagerHome();

		assertEquals("manager/ManagerHome", pageName);

	}

	@Test
	public void testLogout() {

		String pageName = wController.logout(session);
		verify(session).invalidate();
		assertEquals("redirect:/", pageName);

	}

	@Test
	public void testGoToRegister() {

		String pageName = wController.goToRegister(model);
		verify(model).addAttribute(anyString(), anyObject());
		assertEquals("views/Register", pageName);

	}

	
	
	@Test
	public void testDoRegisterForDonorThenReturnHomepage() {

		Donor donor = new Donor();
		String pageName = wController.doRegister(donor, model);
		verify(dDao).addDonor(donor);
		verify(model).addAttribute(anyString(), anyString());
		assertEquals("views/Homepage", pageName);
	}
	
	
	
	
	
	
	
	
	
}
