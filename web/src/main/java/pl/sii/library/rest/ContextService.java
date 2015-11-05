package pl.sii.library.rest;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.ejb3.annotation.SecurityDomain;

import pl.sii.library.domain.model.Context;
import pl.sii.library.domain.persistence.Book;
import pl.sii.library.dto.Request;

@Path("/context")
@Stateless
@SecurityDomain(value = "LDAPAuth")
@RolesAllowed("wroclaw")
public class ContextService {

	private final static String ADMIN_ROLE = "redaktorzy_fb";
	
    @Resource 
    private SessionContext ctx;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Context rentBook(Request<Book> request) {
    	
    	Context context = new Context();
        try {
        	String userName = ctx.getCallerPrincipal().getName();
        	boolean isAdmin = ctx.isCallerInRole(ADMIN_ROLE);
        	context.setUserName(userName);
        	context.setAdmin(isAdmin);
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        return context;
    }
	
    public String getUserName() {
    	return ctx.getCallerPrincipal().getName();
    }
    
    public boolean isUserInRole(String role) {
    	return ctx.isCallerInRole(role);
    }
}
