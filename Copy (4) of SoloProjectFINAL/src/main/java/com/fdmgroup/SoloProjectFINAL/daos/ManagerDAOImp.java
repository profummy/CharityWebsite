package com.fdmgroup.SoloProjectFINAL.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.SoloProjectFINAL.entities.Donation;
import com.fdmgroup.SoloProjectFINAL.entities.Donor;
import com.fdmgroup.SoloProjectFINAL.entities.Manager;
import com.fdmgroup.SoloProjectFINAL.entities.Project;

public class ManagerDAOImp implements ManagerDAO {

	@Autowired
	private EntityManagerFactory factory;

	public ManagerDAOImp() {
	}

	public ManagerDAOImp(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}

	public void addManager(Manager m) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(m);
		manager.getTransaction().commit();

	}

	public void removeManager(String username) {

		EntityManager emanager = factory.createEntityManager();
		emanager.getTransaction().begin();
		Manager manager = emanager.find(Manager.class, username);
		emanager.remove(manager);
		emanager.getTransaction().commit();

	}



	public List<Manager> getAllManagers() {

		EntityManager manager = factory.createEntityManager();
		TypedQuery<Manager> query = manager.createQuery("select m from Manager m", Manager.class);
		List<Manager> managerList = query.getResultList();

		return managerList;
	}

	public List<Donor> getAllDonors() {

		EntityManager manager = factory.createEntityManager();
		TypedQuery<Donor> query = manager.createQuery("select d from Donor d", Donor.class);
		List<Donor> donorList = query.getResultList();

		return donorList;

	}

	public Donor getDonor(String username) {

		EntityManager emanager = factory.createEntityManager();
		Donor donor = emanager.find(Donor.class, username);
		return donor;

	}

	public List<Donation> getAllDonations() {

		EntityManager manager = factory.createEntityManager();
		TypedQuery<Donation> query = manager.createQuery("select d from Donation d", Donation.class);
		List<Donation> donationList = query.getResultList();
		return donationList;

	}

	public List<Project> getAllProjects() {

		EntityManager manager = factory.createEntityManager();
		TypedQuery<Project> query = manager.createQuery("select p from Project p", Project.class);
		List<Project> projectList = query.getResultList();
		return projectList;

	}

	public void addProject(Project p) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(p);
		manager.getTransaction().commit();
		manager.close();

	}

	public void removeProject(int id) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		Project project = manager.find(Project.class, id);
		manager.remove(project);
		manager.getTransaction().commit();

	}

	

	public Project getProjectsByNameAndLocation(String name,String location) {

		EntityManager manager = factory.createEntityManager();
		TypedQuery<Project> query = manager.createQuery("select p from Project p where p.name=? and p.location=?", Project.class);
		query.setParameter(1, name);
		query.setParameter(2,location);
		try{
			Project project = query.getSingleResult();
			return project;
		}
		catch(NoResultException e){
			return null;
		}
	}
}
