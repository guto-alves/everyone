package com.gutotech.everyone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutotech.everyone.model.CreditCard;
import com.gutotech.everyone.model.Customer;
import com.gutotech.everyone.repository.CreditCardRepository;

@Service
public class CreditCardService {
	@Autowired
	private CreditCardRepository repository;

	public CreditCard findById(long cardId) {
		return repository.findById(cardId).orElse(null);
	}

	public List<CreditCard> findAllByCustomer(Customer customer) {
		return repository.findByCustomer(customer);
	}

	public void save(CreditCard card) {
		repository.save(card);
	}

	public void saveAll(Iterable<CreditCard> cards) {
		repository.saveAll(cards);
	}

	public void deleteById(long id) {
		repository.deleteById(id);
	}
}
