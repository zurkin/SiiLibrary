package pl.sii.library.domain.repository;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import pl.sii.library.domain.persistence.Customer;

@Stateless
public class UserRepositoryImpl implements UserRepository {

	@Resource
	private SessionContext ctx;
	
	@Override
	public Customer getUser() {
		String userName = ctx.getCallerPrincipal().getName();
		Customer customer = new Customer(userName);
		return customer;
	}

}
