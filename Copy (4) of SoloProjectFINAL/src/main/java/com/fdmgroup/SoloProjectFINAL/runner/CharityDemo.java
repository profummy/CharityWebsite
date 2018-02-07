package com.fdmgroup.SoloProjectFINAL.runner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.SoloProjectFINAL.daos.DonorDAOImp;
import com.fdmgroup.SoloProjectFINAL.daos.ManagerDAO;
import com.fdmgroup.SoloProjectFINAL.daos.ManagerDAOImp;
import com.fdmgroup.SoloProjectFINAL.daos.ProjectDAOImp;
import com.fdmgroup.SoloProjectFINAL.entities.Manager;



public class CharityDemo {

	public static void main(String[] args) {
		

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersistenceInCreateMode");
		DonorDAOImp donorDao = new DonorDAOImp(factory);
		DonorDAOImp donationDao = new DonorDAOImp (factory);
		ProjectDAOImp projectDao = new ProjectDAOImp (factory);
		ManagerDAOImp managerDao = new ManagerDAOImp (factory);
		
		
		factory.close();
		
	}
}
