package com.fdmgroup.SoloProjectFINAL.daos;


import java.util.List;

import com.fdmgroup.SoloProjectFINAL.entities.Donation;
import com.fdmgroup.SoloProjectFINAL.entities.Donor;
import com.fdmgroup.SoloProjectFINAL.entities.Project;

public interface DonorDAO {

	public void addDonor(Donor d);

	public void removeDonor(String username);

	public void updateDonor(Donor d);
	
	public void addDonation (Donation d);
	
	public List<Project> getAllProjects();
	
	public Donor getDonor(String username);
	
}
