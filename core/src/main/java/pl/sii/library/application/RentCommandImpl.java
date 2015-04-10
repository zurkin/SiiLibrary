package pl.sii.library.application;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import pl.sii.library.domain.model.BookBO;
import pl.sii.library.domain.persistence.Book;
import pl.sii.library.domain.persistence.Customer;
import pl.sii.library.domain.repository.BookRepository;
import pl.sii.library.domain.repository.UserRepository;

@ApplicationScoped
public class RentCommandImpl implements RentCommand {

	@Inject
	private UserRepository userRepository;
	@Inject
	private BookRepository bookRepository;
	
	/* (non-Javadoc)
	 * @see pl.sii.library.application.RentCommand#rentBook(pl.sii.library.model.Book)
	 */
	@Override
	public void rentBook(Book book) {
		Customer customer = userRepository.getUser();
		BookBO bookBO = new BookBO(book);
		bookBO.rent(customer);
		bookRepository.updateBook(book);
	}
	
}
