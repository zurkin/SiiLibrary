package pl.sii.library.application;

import javax.ejb.Local;

import pl.sii.library.domain.dto.ReservationDTO;
import pl.sii.library.domain.persistence.Book;

@Local
public interface RentCommand {

	public abstract void reserveBook(Book book);

	public abstract void rentBook(ReservationDTO reservation);

	public abstract void releaseBook(ReservationDTO reservation);

}