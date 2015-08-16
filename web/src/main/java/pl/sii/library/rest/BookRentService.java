package pl.sii.library.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.sii.library.application.BookQuery;
import pl.sii.library.application.RentCommand;
import pl.sii.library.domain.dto.ReservationDTO;
import pl.sii.library.domain.persistence.Book;
import pl.sii.library.dto.Request;

@Path("/rent")
@RequestScoped
public class BookRentService {
	
	@Inject
	private RentCommand rentCommand;
	@Inject
	private BookQuery bookQuery;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response rentBook(Request<Book> request) {
    	
    	Response.ResponseBuilder builder;

        try {
        	Book book = request.getData();
        	rentCommand.rentBook(book);
            // Create an "ok" response
            builder = Response.ok();
        } catch (Exception e) {
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());        	
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseObj);
        }

        return builder.build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReservationDTO> getAllRents() {
    	
    	return bookQuery.retriveAllBooksReserved();
    	
/*    	List<ReservationDTO> reservations = new ArrayList<ReservationDTO>();
    	Customer person = new Customer("nick testowy", "email@testowy.pl");
		Book book = new Book();
		book.setId(1l);
		book.setAuthor("Autor wyborny");
		book.setDescription("Opis wyborny");
		book.setTitle("Tytul wyborny");
		
		ReservationDTO reservation = new ReservationDTO(1l, person, book);
		reservations.add(reservation);
		
    	person = new Customer("nick testowy 2", "email@testowy.pl2");
		book = new Book();
		book.setId(2l);
		book.setAuthor("Autor wyborny 2");
		book.setDescription("Opis wyborny 2");
		book.setTitle("Tytul wyborny 2");
		
		reservation = new ReservationDTO(2l, person, book);
		reservations.add(reservation);
		
		return reservations;*/
    }
    
	
}
