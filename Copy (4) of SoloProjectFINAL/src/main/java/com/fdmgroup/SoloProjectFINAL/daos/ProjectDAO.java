package com.fdmgroup.SoloProjectFINAL.daos;

import java.util.List;

import com.fdmgroup.SoloProjectFINAL.entities.Project;


public interface ProjectDAO {
	
	public void addProject(Project p);

	public void updateProject(Project p);

	public Project getProject(int id);


	
	
	
}
