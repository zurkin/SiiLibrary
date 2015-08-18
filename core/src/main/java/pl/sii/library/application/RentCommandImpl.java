package pl.sii.library.application;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.sii.library.domain.dto.ReservationDTO;
import pl.sii.library.domain.model.BookBO;
import pl.sii.library.domain.model.RentBO;
import pl.sii.library.domain.persistence.Book;
import pl.sii.library.domain.persistence.Customer;
import pl.sii.library.domain.persistence.Rent;
import pl.sii.library.domain.repository.BookRepository;
import pl.sii.library.domain.repository.UserRepository;

@Stateless
public class RentCommandImpl implements RentCommand {

	@Inject
	private UserRepository userRepository;
	@Inject
	private BookRepository bookRepository;
	
	/* (non-Javadoc)
	 * @see pl.sii.library.application.RentCommand#rentBook(pl.sii.library.model.Book)
	 */
	@Override
	public void reserveBook(Book book) {
		Customer customer = userRepository.getUser();
		BookBO bookBO = new BookBO(book);
		bookBO.rent(customer);
		bookRepository.updateBook(book);
	}

	@Override
	public void rentBook(ReservationDTO reservation) {
		Book book = reservation.getBook();
		Rent rent = book.getRent();
		RentBO rentBO = new RentBO(rent);
		rentBO.rent();
		bookRepository.updateBook(book);
	}

	@Override
	public void releaseBook(ReservationDTO reservation) {
		Book book = reservation.getBook();
		Rent rent = book.getRent();
		RentBO rentBO = new RentBO(rent);
		rentBO.release();
		bookRepository.updateBook(book);
	}
	
}
