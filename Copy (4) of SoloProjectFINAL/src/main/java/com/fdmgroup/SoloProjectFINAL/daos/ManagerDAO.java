package com.fdmgroup.SoloProjectFINAL.daos;

import java.util.List;

import com.fdmgroup.SoloProjectFINAL.entities.Donation;
import com.fdmgroup.SoloProjectFINAL.entities.Donor;
import com.fdmgroup.SoloProjectFINAL.entities.Manager;
import com.fdmgroup.SoloProjectFINAL.entities.Project;

public interface ManagerDAO {

	public void addManager(Manager m);

	public void removeManager(String username);
	
	public void addProject(Project p);
	
	public void removeProject(int id);

	public List<Manager> getAllManagers();

	public Donor getDonor(String username);

	public List<Donor> getAllDonors();
	
	public List<Donation> getAllDonations();
	
	
	public List<Project> getAllProjects();
	
	public Project getProjectsByNameAndLocation(String name,String location);
}
