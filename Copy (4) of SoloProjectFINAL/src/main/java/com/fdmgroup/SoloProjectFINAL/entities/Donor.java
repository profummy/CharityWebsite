package com.fdmgroup.SoloProjectFINAL.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Donor")
public class Donor extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Transient
	private String confirmpassword;
	private String firstname;
	private String lastname;
	private String email;
	@Column(name = "CONTACT_NUMBER")
	private int phonenumber;
	@Column(name = "ADDRESS_LINE1")
	private String address1;
	@Column(name = "ADDRESS_LINE2")
	private String address2;
	private String postcode;
	private String city;
	private String country;
	private String paymentype;
	private int cardnumber;
	@Column(name = "CARD_EXPIRY_MONTH")
	private int monthExpiry;
	@Column(name = "CARD_EXPIRY_YEAR")
	private int yearExpiry;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Donation> donation;

	public Donor() {}

	public Donor(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public Donor(String firstname, String lastname, String email, int phonenumber,
			String address1, String address2, String postcode, String city, String country, String paymentype,
			int cardnumber, int monthExpiry, int yearExpiry, Set<Donation> donation) {
		super();
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phonenumber = phonenumber;
		this.address1 = address1;
		this.address2 = address2;
		this.postcode = postcode;
		this.city = city;
		this.country = country;
		this.paymentype = paymentype;
		this.cardnumber = cardnumber;
		this.monthExpiry = monthExpiry;
		this.yearExpiry = yearExpiry;
		this.donation = donation;
	}
	
	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public Set<Donation> getDonation() {
		return donation;
	}

	public void setDonation(Set<Donation> donation) {
		this.donation = donation;
	}

	public String getPaymentype() {
		return paymentype;
	}

	public void setPaymentype(String paymentype) {
		this.paymentype = paymentype;
	}

	public int getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(int cardnumber) {
		this.cardnumber = cardnumber;
	}

	public int getMonthExpiry() {
		return monthExpiry;
	}

	public void setMonthExpiry(int monthExpiry) {
		this.monthExpiry = monthExpiry;
	}

	public int getYearExpiry() {
		return yearExpiry;
	}

	public void setYearExpiry(int yearExpiry) {
		this.yearExpiry = yearExpiry;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Donor [confirmpassword=" + confirmpassword
				+ ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", phonenumber="
				+ phonenumber + ", address1=" + address1 + ", address2=" + address2 + ", postcode=" + postcode
				+ ", city=" + city + ", country=" + country + ", paymentype=" + paymentype + ", cardnumber="
				+ cardnumber + ", monthExpiry=" + monthExpiry + ", yearExpiry=" + yearExpiry + ", donation=" + donation
				+ "]";
	}

}
