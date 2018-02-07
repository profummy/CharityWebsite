package com.fdmgroup.SoloProjectFINAL;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.SoloProjectFINAL.daos.ManagerDAOImp;
import com.fdmgroup.SoloProjectFINAL.daos.ProjectDAOImp;
import com.fdmgroup.SoloProjectFINAL.entities.Donation;
import com.fdmgroup.SoloProjectFINAL.entities.Donor;
import com.fdmgroup.SoloProjectFINAL.entities.Manager;
import com.fdmgroup.SoloProjectFINAL.entities.Project;
import com.fdmgroup.SoloProjectFINAL.entities.User;

public class ManagerDAOImpTest {



	private EntityManagerFactory factory;
	private EntityManager emanager;
	private EntityTransaction transaction;
	private ManagerDAOImp managerDao;
	private Manager manager;
	private Donor donor;
	private Donation donation;
	private Project project;
	private TypedQuery <Manager> mquery;
	private TypedQuery <Donor> dquery;
	private TypedQuery <Donation> doquery;
	private TypedQuery<Project> pquery;
	
	@Before
	public void setUp(){
		
		factory = mock(EntityManagerFactory.class);
		emanager = mock(EntityManager.class);
		transaction = mock(EntityTransaction.class);
		when(factory.createEntityManager()).thenReturn(emanager); 
		when(emanager.getTransaction()).thenReturn(transaction);
		managerDao = new ManagerDAOImp(factory);
		
		
	}
	

	
	@Test
	public void test_addManager_invokesTransactionMethodsAndPersist() {
		Manager m = new Manager ();
		managerDao.addManager(m);;		
		verify(transaction).begin();
		verify(transaction).commit();
		verify(emanager).persist(m);	
	}
	
	@Test
	public void test_removeManager_invokesTransactionMethodsAndInvokesRemove() {
	
		Manager m = new Manager ();
		managerDao.removeManager(m.getUsername());		
		//Assert
		verify(transaction).begin();
		verify(transaction).commit();
		verify(emanager).remove(m.getUsername());	
		
	}
	
	
	
    @Test
    public void test_getAllManagers_invokesCreateQuery() {
    	manager = mock(Manager.class);
        mquery = mock(TypedQuery.class);
        List<Manager> list = new ArrayList<Manager>();
        when(emanager.createQuery("select m from Manager m",Manager.class)).thenReturn(mquery);
        when(mquery.getResultList()).thenReturn(list);
        // Act

        managerDao.getAllManagers(); 
        // Assert
        verify(emanager).createQuery("select m from Manager m",Manager.class);
 }

       
    
    @Test
    public void test_getAllManagers_returnsNewList() { 
    
    	manager = mock(Manager.class);
        mquery = mock(TypedQuery.class);
        List<Manager> list = new ArrayList<Manager>();
        when(emanager.createQuery("select m from Manager m",Manager.class)).thenReturn(mquery);
        when(mquery.getResultList()).thenReturn(list); 
        // Act
        managerDao.getAllManagers(); 
        // Assert
        verify(emanager).createQuery("select m from Manager m",Manager.class);
        assertEquals(mquery.getResultList(),list);
 
    }
    
    	
    @Test
    public void test_getAllDonors_invokesCreateQuery() {
    	donor = mock(Donor.class);
        dquery = mock(TypedQuery.class);
        List<Donor> list = new ArrayList<Donor>();
        when(emanager.createQuery("select d from Donor d",Donor.class)).thenReturn(dquery);
        when(dquery.getResultList()).thenReturn(list);
        // Act
        managerDao.getAllDonors(); 
        // Assert
        verify(emanager).createQuery("select d from Donor d",Donor.class);
 }	
    	
    	
    	

    @Test
    public void test_getAllDonors_returnsNewList() { 
    
    	donor = mock(Donor.class);
        dquery = mock(TypedQuery.class);
        List<Donor> list = new ArrayList<Donor>();
        when(emanager.createQuery("select d from Donor d",Donor.class)).thenReturn(dquery);
        when(dquery.getResultList()).thenReturn(list); 
        // Act
        managerDao.getAllDonors(); 
        // Assert
        verify(emanager).createQuery("select d from Donor d",Donor.class);
        assertEquals(dquery.getResultList(),list);
 
    }	
    	
	

	@Test
	public void test_getDonor_invokesFind() {
		managerDao.getDonor("donor");
		verify(emanager).find(Donor.class,"donor");
	
	}


@Test
public void test_getDonor_returnsDonorObjectReturnedByFind() { 

	Donor d = new Donor ();
	when(emanager.find(Donor.class, "username")).thenReturn(d);	
	Donor donorRetrieved = managerDao.getDonor("username");	
		assertEquals(d, donorRetrieved);
	
}
	


@Test
public void test_getAllDonations_invokesCreateQuery() {
	donation = mock(Donation.class);
    doquery = mock(TypedQuery.class);
    List<Donation> list = new ArrayList<Donation>();
    when(emanager.createQuery("select d from Donation d",Donation.class)).thenReturn(doquery);
    when(doquery.getResultList()).thenReturn(list);
    // Act
    managerDao.getAllDonations(); 
    // Assert
    verify(emanager).createQuery("select d from Donation d",Donation.class);
}	
	
	
	

@Test
public void test_getAllDonations_returnsNewList() { 
	donation = mock(Donation.class);
    doquery = mock(TypedQuery.class);
    List<Donation> list = new ArrayList<Donation>();
    when(emanager.createQuery("select d from Donation d",Donation.class)).thenReturn(doquery);
    when(doquery.getResultList()).thenReturn(list); 
    // Act
    managerDao.getAllDonations();
    // Assert
    verify(emanager).createQuery("select d from Donation d",Donation.class);
    assertEquals(doquery.getResultList(),list);

}


@Test
public void test_getAllProjects_invokesCreateQuery() {
	project = mock(Project.class);
    pquery = mock(TypedQuery.class);
    List<Project> list = new ArrayList<Project>();
    when(emanager.createQuery("select p from Project p",Project.class)).thenReturn(pquery);
    when(pquery.getResultList()).thenReturn(list);
    // Act
    managerDao.getAllProjects(); 
    // Assert
    verify(emanager).createQuery("select p from Project p",Project.class);
}	
	
	
	

@Test
public void test_getAllProjects_returnsNewList() { 
	project = mock(Project.class);
    pquery = mock(TypedQuery.class);
    List<Project> list = new ArrayList<Project>();
    when(emanager.createQuery("select p from Project p",Project.class)).thenReturn(pquery);
    when(pquery.getResultList()).thenReturn(list); 
    // Act
    managerDao.getAllProjects();
    // Assert
    verify(emanager).createQuery("select p from Project p",Project.class);
    assertEquals(pquery.getResultList(),list);

}



@Test
public void test_addProject_invokesTransactionMethodsAndPersist() {
	
	Project p = new Project ();
	managerDao.addProject(p);		
	verify(transaction).begin();
	verify(transaction).commit();
	verify(emanager).persist(p);	
	
}

@Test
public void test_removeProject_invokesTransactionMethodsAndInvokesRemove() {
	
	Project p = new Project ();
	managerDao.removeProject(p.getId());
	verify(transaction).begin();
	verify(transaction).commit();
	verify(emanager).remove(p.getId());	
	
}






@Test
public void test_getProjectsByNameAndLocation_returnsNewList() { 
	project = mock(Project.class);
    pquery = mock(TypedQuery.class);
    List<Project> list = new ArrayList<Project>();
    when(emanager.createQuery("select p from Project p where p.name=? and p.location=?",Project.class)).thenReturn(pquery);
    when(pquery.getResultList()).thenReturn(list); 
    // Act
    managerDao.getProjectsByNameAndLocation("name", "location");
    // Assert
    verify(emanager).createQuery("select p from Project p where p.name=? and p.location=?",Project.class);
    assertEquals(pquery.getResultList(),list);

}





}
