package com.gutotech.everyone.config.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gutotech.everyone.model.Customer;
import com.gutotech.everyone.repository.CustomerRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = customerRepository.getCustomerByEmail(email);

		if (customer == null) {
			throw new UsernameNotFoundException("Invalid email or password!");
		}

		return new User(customer.getEmail(), customer.getPassword(),
				Arrays.asList(new SimpleGrantedAuthority(customer.getRole())));
	}

}