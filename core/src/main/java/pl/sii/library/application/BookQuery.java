package pl.sii.library.application;

import java.util.List;

import javax.ejb.Local;

import pl.sii.library.domain.dto.ReservationDTO;

@Local
public interface BookQuery {

	public abstract List<ReservationDTO> retriveAllBooksReserved();

}
