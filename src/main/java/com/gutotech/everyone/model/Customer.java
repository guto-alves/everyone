package com.gutotech.everyone.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer {
	@NotBlank(message = "Please provide your email address.")
	@Email
	@Id
	private String email;
	@NotBlank(message = "Please provide a secure password.")
	private String password;
	private boolean enabled;
	private String role;
	@NotBlank(message = "First name must not be blank.")
	private String firstName;
	@NotBlank(message = "Last name must not be blank.")
	private String lastName;
	@NotBlank(message = "Please select a gender.")
	private String gender;
	private boolean saveCards;

	@NotNull(message = "Please provide your birth date.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Basic
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Cart cart;

	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Sale> sales = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<CreditCard> cards = new ArrayList<>();

	public Customer() {
	}

	public Customer(@NotBlank(message = "Please provide your email address.") @Email String email,
			@NotBlank(message = "Please provide a secure password.") String password,
			@NotBlank(message = "First name must not be blank.") String firstName,
			@NotBlank(message = "Last name must not be blank.") String lastName,
			@NotBlank(message = "Please select a gender.") String gender,
			@NotNull(message = "Please provide your birth date.") Date birthDate) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
	}

	public Customer(String email, @NotBlank String password, boolean enabled, String role, @NotBlank String firstName,
			@NotBlank String lastName, @NotBlank String gender, boolean saveCards, @NotNull Date birthDate, Cart cart,
			List<Sale> sales, List<CreditCard> cards) {
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.saveCards = saveCards;
		this.birthDate = birthDate;
		this.cart = cart;
		this.sales = sales;
		this.cards = cards;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isSaveCards() {
		return saveCards;
	}

	public void setSaveCards(boolean saveCards) {
		this.saveCards = saveCards;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public List<CreditCard> getCards() {
		return cards;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
}
