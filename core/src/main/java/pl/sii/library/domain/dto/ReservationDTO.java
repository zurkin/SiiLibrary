package pl.sii.library.domain.dto;

import pl.sii.library.domain.persistence.Book;
import pl.sii.library.domain.persistence.Customer;

public class ReservationDTO {
	
	private Long id;
	private Customer person;
	private Book book;

	public ReservationDTO() {
		super();
	}

	public ReservationDTO(Long id, Customer person, Book book) {
		super();
		this.id = id;
		this.person = person;
		this.book = book;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getPerson() {
		return person;
	}

	public void setPerson(Customer person) {
		this.person = person;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
}
