package pl.sii.library.domain.repository;

import java.util.List;

import javax.ejb.Local;

import pl.sii.library.domain.persistence.Book;

@Local
public interface BookRepository {

	public abstract Book findById(Long id);

	public abstract List<Book> findAllBooks();

	public abstract void updateBook(Book book);

	void create(Book book);

}