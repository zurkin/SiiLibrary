package pl.sii.library.application;

import java.util.List;

import javax.ejb.Local;

import pl.sii.library.domain.dto.ReservationDTO;
import pl.sii.library.domain.persistence.RentStatus;

@Local
public interface BookQuery {

	public abstract List<ReservationDTO> retriveAllBooksReserved();

	List<ReservationDTO> retriveAllBooksReservedByStatus(RentStatus status);

	List<ReservationDTO> retriveAllBooksRented();

}
