package com.fdmgroup.SoloProjectFINAL.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECTS")
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "projectid_sequence", sequenceName = "projectid", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projectid_sequence")

	private int id;
	private String name;
	private String location;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project") // One
																							// project
																							// to
																							// many
																							// donations.
																							// Projects
																							// table
																							// will
																							// have
																							// many
																							// donation
																							// Ids.

	private Set<Donation> donations = new HashSet<Donation>();

	public Project() {
		super();

	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Project(String name, String location) {
		super();
		this.name = name;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Donation> getDonations() {

		return donations;
	}

	public void addDonation(Donation donation) {

		donations.add(donation);
		donation.setProject(this);
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", donation=" + "]";
	}

}
