package pl.sii.library.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.ejb3.annotation.SecurityDomain;

import pl.sii.library.data.BookListProducer;
import pl.sii.library.domain.persistence.Book;
import pl.sii.library.domain.repository.BookRepository;
import pl.sii.library.dto.Request;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the Book table.
 */
@Path("/books")
@Stateless
@SecurityDomain(value = "LDAPAuth")
@RolesAllowed("wroclaw")
public class BookResourceRESTService {
    @Inject
    private Logger log;

    @Inject
    private Validator validator;

    @Inject
    private BookRepository repository;

    @Inject
    private BookListProducer bookProducer;

/*    @Inject
    private BookQuery bookQuery;
*/    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> listAllBooks() {
    	
//    	return bookQuery.retriveAllBooks();
//    	String userName = ctx.getUserName();
//    	ctx.isUserInRole("WROCLAW_ROLE");
//    	context.setUserName(userName);
//    	context.setAdmin(true);

    	return bookProducer.getBooks();
//        return repository.findAllBooks();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book lookupBookById(@PathParam("id") long id) {
        Book book = repository.findById(id);
        if (book == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return book;
    }

    /**
     * Creates a new book from the values provided.
     * Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("redaktorzy_fb")
    public Response createBook(Request<Book> request) {

        Response.ResponseBuilder builder;

        try {
            /* Validates book using bean validation */
        	Book book = request.getData();
            validateBook(book);

            repository.create(book);

            // Create an "ok" response
            builder = Response.ok();
        } catch (ConstraintViolationException ce) {
            // Handle bean validation issues
            builder = createViolationResponse(ce.getConstraintViolations());
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<String, String>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }

        return builder.build();
    }

    /**
     * <p>
     * Validates the given Book variable and throws validation exceptions based on the type of error. If the error is standard
     * bean validation errors then it will throw a ConstraintValidationException with the set of the constraints violated.
     * </p>
     *
     *  @param book Book to be validated
     * @throws ConstraintViolationException If Bean Validation errors exist
     */
    private void validateBook(Book book) throws ConstraintViolationException {
        // Create a bean validator and check for issues.
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }

    }

    /**
     * Creates a JAX-RS "Bad Request" response including a map of all violation fields, and their message. This can then be used
     * by clients to show violations.
     * 
     * @param violations A set of violations that needs to be reported
     * @return JAX-RS response containing all violations
     */
    private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
        log.fine("Validation completed. violations found: " + violations.size());

        Map<String, String> responseObj = new HashMap<String, String>();

        for (ConstraintViolation<?> violation : violations) {
            responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }


}
