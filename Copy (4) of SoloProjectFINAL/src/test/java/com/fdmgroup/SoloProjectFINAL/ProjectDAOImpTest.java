package com.fdmgroup.SoloProjectFINAL;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.SoloProjectFINAL.daos.ProjectDAOImp;
import com.fdmgroup.SoloProjectFINAL.entities.Project;

public class ProjectDAOImpTest {

	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;
	private ProjectDAOImp projectDao;

	@Before
	public void setUp() {

		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		when(factory.createEntityManager()).thenReturn(manager); // stubbing
		when(manager.getTransaction()).thenReturn(transaction);
		projectDao = new ProjectDAOImp(factory);

	}

	@Test
	public void test_addProject_invokesTransactionMethodsAndPersist() {
		Project p = new Project();
		projectDao.addProject(p);
		;
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).persist(p);

	}

	@Test
	public void test_updateProject_invokesTransactionMethodsAndMerge() {
		Project p = new Project();
		projectDao.addProject(p);
		projectDao.updateProject(p);
		verify(transaction, times(2)).begin();
		verify(transaction, times(2)).commit();
		verify(manager).merge(p);

	}

	@Test
	public void test_getProject_invokesFind() {
		projectDao.getProject(1);
		verify(manager).find(Project.class, 1);
	}

	@Test // JUnit test as this method as a return type
	public void test_getProject_returnsProjectObjectReturnedByFind() { 
																																	
		Project p = new Project();
		when(manager.find(Project.class, 1)).thenReturn(p);
		Project projectRetrieved = projectDao.getProject(1);
		assertEquals(p, projectRetrieved);

	}

} 
