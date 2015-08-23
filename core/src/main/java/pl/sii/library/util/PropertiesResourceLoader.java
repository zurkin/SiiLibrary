package pl.sii.library.util;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class PropertiesResourceLoader {

    @Produces
    @PropertiesResource(name = "", loader = "")
    Properties loadProperties(InjectionPoint ip) {
//        System.out.println("-- called PropertiesResource loader");
        PropertiesResource annotation = ip.getAnnotated().getAnnotation(
                PropertiesResource.class);
        String fileName = annotation.name();
//        String loader = annotation.loader();
        Properties props = null;
        // Load the properties from file
        URL url = null;
        url = Thread.currentThread().getContextClassLoader()
                .getResource(fileName);
        if (url != null) {
            props = new Properties();
            try {
                props.load(url.openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return props;
    }
}