package com.gutotech.everyone.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Clothe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	@Column(nullable = false)
	private String name;

	@Column(length = 500)
	private String description;

	@NotNull
	private double price = 10;

	@NotNull
	private int discount;

	private int stock = 1;

	@URL
	private String imageUrl;

	@ManyToOne
	private Category category;

	@ManyToOne
	private Color color;

	@ManyToOne
	private Brand brand;

	@OneToMany(mappedBy = "id.clothe")
	private Set<ClotheSize> sizes = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "clothe")
	private List<Review> review = new ArrayList<>();

	public Clothe() {
	}

	public Clothe(String name, String description, double price, int discount, int stock, String imageUrl,
			Category category, Color color, Brand brand) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.discount = discount;
		this.stock = stock;
		this.imageUrl = imageUrl;
		this.category = category;
		this.color = color;
		this.brand = brand;
	}

	public Clothe(long id, String name, String description, double price, Color color, int stock, Brand brand,
			Category category, List<Review> review) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.color = color;
		this.stock = stock;
		this.brand = brand;
		this.category = category;
		this.review = review;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Review> getReviews() {
		return review;
	}

	public Set<ClotheSize> getSizes() {
		return sizes;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Clothe other = (Clothe) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
