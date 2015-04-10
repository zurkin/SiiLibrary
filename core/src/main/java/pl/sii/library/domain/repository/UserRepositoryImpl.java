package pl.sii.library.domain.repository;

import javax.enterprise.context.ApplicationScoped;

import pl.sii.library.domain.persistence.Customer;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

	@Override
	public Customer getUser() {
		Customer customer = new Customer("kholdanowicz", "holdanowicz@gmail.com");
		return customer;
	}

}
