package com.gutotech.everyone.model;

public class PaymentFormDto {
	private String method;
	private CreditCard selectedCard = new CreditCard();
	private boolean rememberCard = true;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public CreditCard getSelectedCard() {
		return selectedCard;
	}

	public void setSelectedCard(CreditCard selectedCard) {
		this.selectedCard = selectedCard;
	}

	public boolean isRememberCard() {
		return rememberCard;
	}

	public void setRememberCard(boolean rememberCard) {
		this.rememberCard = rememberCard;
	}

}
