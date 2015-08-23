package pl.sii.library.service;

import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import pl.sii.library.util.PropertiesResource;
import static pl.sii.library.util.ConfigurationProperties.*;

@ApplicationScoped
public class ConfigurationService {

	@Inject
	@PropertiesResource(name = "rentconfig.properties", loader = "")
	private Properties props;	

	public int getRentDuration() {
		return Integer.parseInt(props.getProperty(RENT_DURATION.getName()));
	}
	
}
