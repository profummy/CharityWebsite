package com.fdmgroup.SoloProjectFINAL;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.SoloProjectFINAL.daos.DonorDAOImp;
import com.fdmgroup.SoloProjectFINAL.entities.Donation;
import com.fdmgroup.SoloProjectFINAL.entities.Donor;
import com.fdmgroup.SoloProjectFINAL.entities.Project;

public class DonorDAOImpTest {

	
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;
	private DonorDAOImp donorDao;
	private Project project;
	private TypedQuery<Project> pquery;
	
	
	@Before
	public void setUp(){
		
		factory = mock(EntityManagerFactory.class);
		manager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		when(factory.createEntityManager()).thenReturn(manager); //stubbing
		when(manager.getTransaction()).thenReturn(transaction);
		donorDao = new DonorDAOImp(factory);	
	}
	
	
	@Test
	public void test_addDonor_invokesTransactionMethodsAndPersist() {
		
		Donor d = new Donor ();
		donorDao.addDonor(d);		
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).persist(d);	
		
	}
	
	@Test
	public void test_removeDonor_invokesTransactionMethodsAndInvokesRemove() {
		
		Donor d = new Donor ();
		donorDao.removeDonor(d.getUsername());
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).remove(d.getUsername());	
		
	}
	
	
	@Test
	public void test_updateDonor_invokesTransactionMethodsAndMerge() {
		
		Donor d = new Donor ();
		donorDao.addDonor(d);
		donorDao.updateDonor(d);
		verify(transaction,times(2)).begin();
		verify(transaction,times(2)).commit();
		verify(manager).merge(d);	
		
	}
	

	@Test
	public void test_addDonation_invokesTransactionMethodsAndPersist() {
		
		Donation d = new Donation ();
		donorDao.addDonation(d);		
		verify(transaction).begin();
		verify(transaction).commit();
		verify(manager).persist(d);	
		
	}
	
	
	@Test
	public void test_getAllProjects_invokesCreateQuery() {
		project = mock(Project.class);
	    pquery = mock(TypedQuery.class);
	    List<Project> list = new ArrayList<Project>();
	    when(manager.createQuery("select p from Project p",Project.class)).thenReturn(pquery);
	    when(pquery.getResultList()).thenReturn(list);
	    donorDao.getAllProjects(); 
	    verify(manager).createQuery("select p from Project p",Project.class);
	}	
		
		
		

	@Test
	public void test_getAllProjects_returnsNewList() { 
		project = mock(Project.class);
	    pquery = mock(TypedQuery.class);
	    List<Project> list = new ArrayList<Project>();
	    when(manager.createQuery("select p from Project p",Project.class)).thenReturn(pquery);
	    when(pquery.getResultList()).thenReturn(list); 
	    donorDao.getAllProjects();
	   
	    verify(manager).createQuery("select p from Project p",Project.class);
	    assertEquals(pquery.getResultList(),list);

	}
	
	

	
	@Test
	public void test_getDonor_invokesFind() {
		donorDao.getDonor("donor");
		verify(manager).find(Donor.class,"donor");
	
	}
	
	
	
	
	@Test
	public void test_getDonor_returnsDonorObjectReturnedByFind() { 

		Donor d = new Donor ();
		when(manager.find(Donor.class, "username")).thenReturn(d);	
		Donor donorRetrieved = donorDao.getDonor("username");	
			assertEquals(d, donorRetrieved);
		
	}
	
	
	
	
	
	
	

	
	

}
