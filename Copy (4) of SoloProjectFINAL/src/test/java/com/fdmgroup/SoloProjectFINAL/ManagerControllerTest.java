package com.fdmgroup.SoloProjectFINAL;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.SoloProjectFINAL.controllers.ManagerController;
import com.fdmgroup.SoloProjectFINAL.daos.ManagerDAOImp;
import com.fdmgroup.SoloProjectFINAL.daos.ProjectDAOImp;
import com.fdmgroup.SoloProjectFINAL.entities.Donation;
import com.fdmgroup.SoloProjectFINAL.entities.Donor;
import com.fdmgroup.SoloProjectFINAL.entities.Manager;
import com.fdmgroup.SoloProjectFINAL.entities.Project;

public class ManagerControllerTest {

	private ManagerDAOImp mDao;
	private ProjectDAOImp pDao;
	private ManagerController mController;
	private Model model;
	private HttpSession session;
	private List<Manager> mlist;
	private List<Donor> dlist;
	private List<Donation>dolist;
	private List <Project>plist;
	private String username;
	private Donor donor;
	private Project project;
	
	@Before
	public void setUp(){
		mDao=mock(ManagerDAOImp.class);
		pDao=mock(ProjectDAOImp.class);
		model = mock (Model.class);
		session = mock (HttpSession.class);
		mController = new ManagerController(mDao);
		donor =mock(Donor.class);
		
		
		
	}
	
	@Test
	public void testGoToManager_ManagerHome() {
		//Act - Method you want to test
		String pageName = mController.goToManagerHome();			
		//Assert
		assertEquals("manager/ManagerHome",pageName);
		
	}

	
	@Test
	public void testGoToManagerHomeSession() {
		
		//Act - Method you want to test
		String pageName = mController.goToManagerHome("username", session);	
		//Assert
		assertEquals("manager/ManagerHome",pageName);
		
	}
	
	
	@Test
	public void testGoToIndexAndLogout() {
		
		String pageName = mController.logout(session);
		
		verify(session).invalidate();
		assertEquals("redirect:/",pageName);
		
	}
	
	
	@Test
	public void testgoToAddManager() {
		
		String pageName = mController.goToAddManager(model);
		verify(model).addAttribute(anyString(),anyObject());
		assertEquals("manager/AddManager",pageName);
		
	}
	
	
	@Test
	public void testProcessAddManager(){
		
		//Act - Method you want to test
		Manager manager = new Manager();
		String pageName = mController.processAddManager(manager,model);
		//Assert
		verify(mDao).addManager(manager); // mDao is mock
		verify(model).addAttribute("message", "Manager added successfully"); // Can be specific with attributes
		assertEquals("manager/ManagerHome",pageName);
	}
	
	

	
	
	@Test
	public void testgoToRemoveManager() {
		
		String pageName = mController.goToRemoveManager(model);
		verify(model).addAttribute(anyString(), anyObject());
		assertEquals("manager/RemoveManager",pageName);
		
	}
	
	

	@Test
	public void testProcessRemoveManagerAndReturnsHome(){
		
		//Act - Method you want to test
		Manager manager = new Manager();
		String pageName = mController.processRemoveManager(manager,model);
		//Assert
		verify(mDao).removeManager(manager.getUsername());
		verify(model).addAttribute("message2", "Manager removed successfully"); 
		assertEquals("manager/ManagerHome",pageName);
	}
	
	
	
	
	@Test
	public void testgoToShowManager() {
		
		mlist= mDao.getAllManagers();
		String pageName = mController.goToShowManagers(model);
		verify(model).addAttribute(anyString(), anyObject());
		assertEquals("manager/ShowManagers",pageName);
		
	}
	
	
	@Test
	public void testProcessShowManagerAndReturnsHome(){
		
		//Act - Method you want to test
		Manager manager = new Manager();
		String pageName = mController.processShowManagers(manager, model);
		//Assert
		verify(mDao).getAllManagers();
		verify(model).addAttribute("message4", "List of managers found"); 
		assertEquals("manager/ManagerHome",pageName);
	}
	
	
	@Test
	public void testgoToSearchDonor() {
		String pageName = mController.goToSearchDonor(model);
		assertEquals("manager/SearchDonor",pageName);
		
	}
	
	
	@Test
	public void testProcessSearchDonor(){
		
		String pageName = mController.processShowDonor("donorName", model);
		//Assert
		verify(mDao).getDonor("donorName");
		verify(model).addAttribute(anyString(), anyString()); 
		assertEquals("manager/ShowDonor",pageName);
	}
	
	
	@Test
	public void testgoToShowDonor() {
		
		Donor donor = new Donor();
		String pageName = mController.goToShowDonor(model, username);
		donor=mDao.getDonor(username);
		verify(model).addAttribute(anyString(), anyString());
		assertEquals("manager/showDonor",pageName);
		
	}
	
	
	@Test
	public void testgoToShowDonors() {
		
		dlist= mDao.getAllDonors();
		String pageName = mController.goToShowDonors(model);
		verify(model).addAttribute(anyString(), anyObject());
		assertEquals("manager/ShowDonors",pageName);
		
	}
	
	
	@Test
	public void testProcessShowDonorsAndReturnsHome(){
		
		//Act - Method you want to test
		String pageName = mController.processShowDonors(model);
		//Assert
		verify(mDao).getAllDonors();
		verify(model).addAttribute(anyString(), anyString()); 
		assertEquals("manager/ManagerHome",pageName);
	}
	
	
	@Test
	public void testgoToShowDonations() {
		
		dolist= mDao.getAllDonations();
		String pageName = mController.goToShowDonations(model);
		verify(model).addAttribute(anyString(), anyObject());
		assertEquals("manager/ShowDonations",pageName);
		
	}
	
	
	
	@Test
	public void testProcessShowDonationsAndReturnsHome(){
		
		//Act - Method you want to test
		String pageName = mController.processShowDonations(model);
		//Assert
		verify(mDao).getAllDonations();
		verify(model).addAttribute(anyString(), anyString()); 
		assertEquals("manager/ManagerHome",pageName);
	}
	
	
	@Test
	public void testgoToManageProjects() {
		
		plist= mDao.getAllProjects();
		String pageName = mController.goToManageProjects(model);
		verify(model).addAttribute(anyString(), anyObject());
		assertEquals("manager/ManageProjects",pageName);
		
	}
	
	
	@Test
	public void testgoToUpdateProjects() {
		
		
		
		plist= mDao.getAllProjects();
		String pageName = mController.goToUpdateProject(project.getId(), model);
		verify(pDao).getProject(project.getId());
		assertEquals("manager/UpdateProject",pageName);
		
	}
	
	
	
	@Test
	public void testProcessUpdateProject(){
		
		//Act - Method you want to test
		Project project = new Project();
		String pageName = mController.processUpdateProject(project, model);
		//Assert
		verify(pDao).updateProject(project);
		verify(model).addAttribute(anyString(), anyString()); 
		verify(model).addAttribute(anyString(),anyObject());
		assertEquals("manager/UpdateProject",pageName);
	}
	
	
	
	
	@Test
	public void testgoToAddProject() {
		
		String pageName = mController.goToAddProject(model);
		verify(model).addAttribute(anyString(),anyObject());
		assertEquals("manager/AddProject",pageName);
		
	}
	
	
	@Test
	public void testProcessAddProject(){
		
		
		Project project = new Project();
		String pageName = mController.processAddProject(project,model);
		//Assert
		verify(mDao).addProject(project); 
		verify(model).addAttribute(anyString(), anyString()); 
		assertEquals("manager/ManagerHome",pageName);
	}
	

	
	
	@Test
	public void testgoToRemoveProject() {
		
		String pageName = mController.goToRemoveProject(model);
		verify(model).addAttribute(anyString(), anyObject());
		assertEquals("manager/RemoveProject",pageName);
		
	}
	
	

	@Test
	public void testProcessRemoveProject(){
		
		//Act - Method you want to test
		Project project = new Project();
		String pageName = mController.processRemoveProject(project, model);
		//Assert
		verify(mDao).removeProject(project.getId());
		verify(model).addAttribute(anyString(), anyString()); 
		assertEquals("manager/ManagerHome",pageName);
	}
	
	
	
	
	
	
	
	
	
}
