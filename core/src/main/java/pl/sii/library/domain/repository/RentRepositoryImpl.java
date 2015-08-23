package pl.sii.library.domain.repository;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.sii.library.domain.persistence.Book;
import pl.sii.library.domain.persistence.Rent;

@Stateless
public class RentRepositoryImpl implements RentRepository {

    @PersistenceContext(name = "libraryPU")
    private EntityManager em;
    
    @Inject
    private Event<Book> bookEventSrc;	
    
    @Override
    public void updateRent(Book book) {
    	Rent rent = book.getRent();
    	em.merge(rent);
    	bookEventSrc.fire(book);
    }
    
	
}
