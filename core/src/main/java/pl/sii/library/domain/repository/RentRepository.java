package pl.sii.library.domain.repository;

import javax.ejb.Local;

import pl.sii.library.domain.persistence.Book;

@Local
public interface RentRepository {

	void updateRent(Book book);

}
