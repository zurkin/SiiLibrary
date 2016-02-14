package pl.sii.library.application;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

import pl.sii.library.domain.persistence.Book;
import pl.sii.library.domain.repository.BookRepository;

@ApplicationScoped
public class BookExpiredQueryImpl implements BookExpiredQuery {

	@EJB
	private BookRepository bookRepository;

	@Override
	public List<Book> checkForExpiredIn3Days() {
		List<Book> allExpiredBooks = bookRepository.findAllExpiredIn3Days();
		return allExpiredBooks;
	}
	
	
	@Override
	public List<Book> checkForExpiredAfterWeek() {
		List<Book> allExpiredBooks = bookRepository.findAllExpiredBooksAfterWeek();
		return allExpiredBooks;
	}

	
	
}
