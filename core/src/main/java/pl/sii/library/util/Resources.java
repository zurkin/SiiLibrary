package pl.sii.library.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

/**
* This class uses CDI to alias Java EE resources, such as the persistence context, to CDI beans
*
* <p>
* Example injection on a managed bean field:
* </p>
*
* <pre>
* &#064;Inject
* private EntityManager em;
* </pre>
*/
public class Resources {

    @SuppressWarnings("unused")
    @Produces
    @PersistenceContext(name = "libraryPU")
    private EntityManager em;

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }


}