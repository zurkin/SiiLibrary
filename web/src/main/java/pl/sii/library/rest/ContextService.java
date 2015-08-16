package pl.sii.library.rest;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.sii.library.domain.model.Context;
import pl.sii.library.domain.persistence.Book;
import pl.sii.library.dto.Request;

@Path("/context")
@Stateless
public class ContextService {

    @Resource 
    private SessionContext ctx;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Context rentBook(Request<Book> request) {
    	
    	Context context = new Context();
        try {
        	String userName = ctx.getCallerPrincipal().getName();
        	context.setUserName(userName);
//        	ctx.isCallerInRole("");
        	context.setAdmin(true);
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        return context;
    }
	
}
