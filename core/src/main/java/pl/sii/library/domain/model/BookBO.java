package pl.sii.library.domain.model;

import java.util.Calendar;
import java.util.Date;

import pl.sii.library.domain.dto.ReservationDTO;
import pl.sii.library.domain.persistence.Book;
import pl.sii.library.domain.persistence.Customer;
import pl.sii.library.domain.persistence.Rent;
import pl.sii.library.domain.persistence.RentQue;

public class BookBO extends BaseBusinessObject<Book> {

	private static final int RENT_DURATION = 2;
	
	public BookBO(Book entity) {
		super(entity, Book.class);
	}

	public void rent(Customer customer) {
		if (bookAvailable()) {
			Date startRentDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(startRentDate);
			cal.add(Calendar.WEEK_OF_YEAR, RENT_DURATION);
			Date endRentDate = cal.getTime();
			Rent rent = new Rent(customer, startRentDate, endRentDate);
			getEntity().setRent(rent);
		} else {
			RentQue que = new RentQue(customer);
			getEntity().getRentQue().add(que);
		}
	}

	private boolean bookAvailable() {
		return getEntity().getRent() == null;
	}

	public ReservationDTO prepareReservationView() {
		ReservationDTO reservation = new ReservationDTO(entity.getRent().getId(), entity.getRent().getCustomer(), entity);
		return reservation;
	}

	
	
}
