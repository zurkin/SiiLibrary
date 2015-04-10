package pl.sii.library.rest;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.sii.library.application.RentCommand;
import pl.sii.library.domain.persistence.Book;
import pl.sii.library.dto.Request;

@Path("/rent")
@RequestScoped
public class BookRentService {
	
	@Inject
	private RentCommand rentCommand;

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
	
}
