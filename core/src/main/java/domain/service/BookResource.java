package domain.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import domain.dto.Book;
import domain.repository.BookRepository;

@Path("/book")
@Stateless
@Produces("application/json")
public class BookResource {

	@EJB
	private BookRepository bookRepository;
	
	@GET
//	@Path("/{param}")
	public List<Book> printMessage(/*@PathParam("param") String msg*/) {
 
//		String result = "Restful example : " + msg;
		List<Book> books = bookRepository.getAllBooks();
 
//		return Response.status(200).entity(books).build();
		return books;
 
	}
	
}
