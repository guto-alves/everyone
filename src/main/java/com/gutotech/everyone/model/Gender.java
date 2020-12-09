package com.gutotech.everyone.model;

public enum Gender {
	MALE("Male"), FEMALE("Female"), BOY("Boy"), GIRL("Girl");

	private final String name;

	private Gender(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
