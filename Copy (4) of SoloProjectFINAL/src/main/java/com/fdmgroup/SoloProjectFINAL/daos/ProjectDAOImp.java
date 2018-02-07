package com.fdmgroup.SoloProjectFINAL.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.SoloProjectFINAL.entities.Manager;
import com.fdmgroup.SoloProjectFINAL.entities.Project;


public class ProjectDAOImp implements ProjectDAO {

	@Autowired
	private EntityManagerFactory factory;
	
	

	public ProjectDAOImp() {}



	public ProjectDAOImp(EntityManagerFactory factory) {
		super();
		this.factory = factory;
	}
	
	
	public void addProject(Project p) {
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(p);
		manager.getTransaction().commit();

	}
	
	

	public void updateProject(Project p) {
		
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(p); //
		manager.getTransaction().commit();
		
	}

	public Project getProject(int id) {
		
		EntityManager manager = factory.createEntityManager();
		Project project = manager.find(Project.class,id); 
		return project;
		
	}

	
 

}
