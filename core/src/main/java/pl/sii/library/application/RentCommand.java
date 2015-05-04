package pl.sii.library.application;

import javax.ejb.Local;

import pl.sii.library.domain.persistence.Book;

@Local
public interface RentCommand {

	public abstract void rentBook(Book book);

}