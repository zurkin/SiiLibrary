package pl.sii.library.application;

import pl.sii.library.domain.persistence.Book;

public interface RentCommand {

	public abstract void rentBook(Book book);

}