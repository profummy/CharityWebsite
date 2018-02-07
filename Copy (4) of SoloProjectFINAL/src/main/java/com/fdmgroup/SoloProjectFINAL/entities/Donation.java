package com.fdmgroup.SoloProjectFINAL.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name= "DONATIONS")
public class Donation implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="donationid_sequence",
	sequenceName="donationid", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="donationid_sequence")
	
	private int id;
	private double amount;
	@Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

	
	@ManyToOne (fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	private Project project;
	
	
	public Donation() {
		super();
		this.dateTime = new Date();
	}


	
	
	
	public Donation(int id, double amount, Date dateTime, Project project) {
		super();
		this.id = id;
		this.amount = amount;
		this.dateTime = dateTime;
		this.project = project;
	}





	public Date getDateTime() {
		return dateTime;
	}





	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}



	
	
	
	
	
	
}
