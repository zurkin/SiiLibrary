package pl.sii.library.application;

import java.util.List;

import pl.sii.library.domain.persistence.Book;

public interface BookExpiredQuery {

	List<Book> checkForExpiredIn3Days();
	List<Book> checkForExpiredAfterWeek();

}