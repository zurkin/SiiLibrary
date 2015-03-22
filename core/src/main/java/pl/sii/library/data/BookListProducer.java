package pl.sii.library.data;

import pl.sii.library.model.Book;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
public class BookListProducer {
    @Inject
    private BookRepository bookRepository;

    private List<Book> books;

    // @Named provides access the return value via the EL variable name "books" in the UI (e.g., Facelets or JSP view)
    @Produces
    @Named
    public List<Book> getBooks() {
        return books;
    }

    public void onBookListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Book book) {
        retrieveAllBooks();
    }

    @PostConstruct
    public void retrieveAllBooks() {
        books = bookRepository.findAllBooks();
    }
}
