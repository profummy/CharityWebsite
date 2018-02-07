package com.fdmgroup.SoloProjectFINAL.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Manager")
public class Manager extends User implements Serializable{

	public Manager() {}

	public Manager(String username, String password) {
		super(username,password);
	}

	private static final long serialVersionUID = 1L;
}
