package pl.sii.library.domain.model;

import pl.sii.library.domain.dto.ReservationDTO;
import pl.sii.library.domain.persistence.Book;
import pl.sii.library.domain.persistence.Customer;
import pl.sii.library.domain.persistence.Rent;
import pl.sii.library.domain.persistence.RentStatus;

public class BookBO extends BaseBusinessObject<Book> {

	public BookBO(Book entity) {
		super(entity, Book.class);
	}

	public void reserve(Customer customer) {
		if (bookAvailable()) {
			Rent rent = new Rent(customer);
			getEntity().setRent(rent);
		} /*else {
			RentQue que = new RentQue(customer);
			getEntity().getRentQue().add(que);
		}*/
	}

	private boolean bookAvailable() {
		Rent rentEntity = getEntity().getRent();
		return rentEntity == null || RentStatus.RELEASED == rentEntity.getStatus();
	}

	public ReservationDTO prepareReservationView() {
		ReservationDTO reservation = new ReservationDTO(entity.getRent().getId(), entity.getRent().getCustomer(), entity);
		return reservation;
	}

/*	public BookDTO prepareView() {
		BookDTO view = new BookDTO();
		view.setId(getEntity().getId());
		view.setAuthor(getEntity().getAuthor());
		view.setTitte(getEntity().getTitle());
		view.setDescription(getEntity().getDescription());
		Rent rent = getEntity().getRent();
		if (rent != null) {
			RentBO rentBO = new RentBO(rent);
			RentDTO rentDTO = rentBO.prepareView();
			view.setRent(rentDTO);
			Date endDate = getEntity().getRent().getEndDate();
			Date availabilityDate = DateUtil.addDays(endDate, 1);
			view.setAvailabilityDate(availabilityDate);
		}
		boolean available = rent == null || RELEASED == rent.getStatus();
		view.setAvailable(available);
		return view;
	}*/

	
	
}
