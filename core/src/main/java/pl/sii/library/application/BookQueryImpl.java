package pl.sii.library.application;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.sii.library.domain.dto.ReservationDTO;
import pl.sii.library.domain.model.BookBO;
import pl.sii.library.domain.persistence.Book;
import pl.sii.library.domain.persistence.RentStatus;
import pl.sii.library.domain.repository.BookRepository;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Stateless
public class BookQueryImpl implements BookQuery {
	
/*    @Inject
    private BookListProducer bookProducer;
*/	
	@EJB
	private BookRepository bookRepository;
	
/*	@Override
	public List<BookDTO>  retriveAllBooks() {
		List<Book> books = bookProducer.getBooks();
		List<BookDTO> booksView = Lists.transform(books, new Function<Book, BookDTO>() {
			@Override
			public BookDTO apply(Book book) {
				BookBO bookBO = new BookBO(book);
				BookDTO bookView = bookBO.prepareView();
				return bookView;
			}
		});
		return booksView;
	}*/
	
	@Override
	public List<ReservationDTO> retriveAllBooksReserved() {
		return retriveAllBooksReservedByStatus(RentStatus.RESERVED);
	}
	
	@Override
	public List<ReservationDTO> retriveAllBooksRented() {
		return retriveAllBooksReservedByStatus(RentStatus.RENTED);
	}
	
	@Override
	public List<ReservationDTO> retriveAllBooksReservedByStatus(RentStatus status) {
		List<Book> reservations = bookRepository.findAllReservedBooks(status);
		List<ReservationDTO> reservationsView = Lists.transform(reservations, new Transofrmation<Book, ReservationDTO>());
		return reservationsView;
	}

	@Override
	public List<ReservationDTO> retriveAllBooksExpired() {
		List<Book> reservations = bookRepository.findAllExpiredBooks();
		List<ReservationDTO> reservationsView = Lists.transform(reservations, new Transofrmation<Book, ReservationDTO>());
		return reservationsView;
	}
	
	@SuppressWarnings("hiding")
	private class Transofrmation<Book, ReservationDTO> implements Function<pl.sii.library.domain.persistence.Book, pl.sii.library.domain.dto.ReservationDTO> {

		@Override
		public pl.sii.library.domain.dto.ReservationDTO apply(pl.sii.library.domain.persistence.Book book) {
			BookBO bookBO = new BookBO(book);
			pl.sii.library.domain.dto.ReservationDTO reservationView = bookBO.prepareReservationView();
			return reservationView;
		}
		
	}

}
