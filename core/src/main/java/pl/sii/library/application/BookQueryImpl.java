package pl.sii.library.application;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.sii.library.domain.dto.ReservationDTO;
import pl.sii.library.domain.model.BookBO;
import pl.sii.library.domain.persistence.Book;
import pl.sii.library.domain.repository.BookRepository;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Stateless
public class BookQueryImpl implements BookQuery {
	
	@EJB
	private BookRepository bookRepository;
	
	@Override
	public List<ReservationDTO> retriveAllBooksReserved() {
		List<Book> reservations = bookRepository.findAllReservedBooks();
		List<ReservationDTO> reservationsView = Lists.transform(reservations, new Function<Book, ReservationDTO>() {
			@Override
			public ReservationDTO apply(Book book) {
				BookBO bookBO = new BookBO(book);
				ReservationDTO reservationView = bookBO.prepareReservationView();
				return reservationView;
			}
		});
		return reservationsView;
	}

}
