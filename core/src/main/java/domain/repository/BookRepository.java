package domain.repository;

import java.util.List;

import javax.ejb.Local;

import domain.dto.Book;

@Local
public interface BookRepository {

	public abstract List<Book> getAllBooks();

}