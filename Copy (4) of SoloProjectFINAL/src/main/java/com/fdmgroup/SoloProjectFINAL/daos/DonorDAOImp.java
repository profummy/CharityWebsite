package com.fdmgroup.SoloProjectFINAL.daos;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.SoloProjectFINAL.entities.Donation;
import com.fdmgroup.SoloProjectFINAL.entities.Donor;
import com.fdmgroup.SoloProjectFINAL.entities.Project;
import com.fdmgroup.SoloProjectFINAL.entities.User;

public class DonorDAOImp implements DonorDAO {

	@Autowired
	private EntityManagerFactory factory;

	public DonorDAOImp() {}

	public DonorDAOImp(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}

	public void addDonor(Donor d) {

		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(d);
		manager.getTransaction().commit();

	}

	public void removeDonor(String username) {

		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		User donor = manager.find(User.class,username);
		manager.remove(donor);
		manager.getTransaction().commit();

	}

	public void updateDonor(Donor d) {

		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(d); //
		manager.getTransaction().commit();

	}


	public void addDonation(Donation d) {
		
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(d);
		manager.getTransaction().commit();

	}

	
	public List<Project> getAllProjects() {
		
		
		 EntityManager manager = factory.createEntityManager();
		 TypedQuery<Project> query = manager.createQuery("select p from Project p", Project.class);
		 List<Project> projectList = query.getResultList();
		return projectList;
		
	
}
	
	public Donor getDonor(String username) {

		EntityManager manager = factory.createEntityManager();
		Donor donor = manager.find(Donor.class, username);
		
		return donor;
	}
	
	

}
