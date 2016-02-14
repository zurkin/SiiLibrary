package pl.sii.library.service;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

@Singleton
public class TemplateService {

	private static final String RENT_TEMPLATE_NAME = "rent.ftl";
	private static final String BEFORE_OVERDUE_TEMPLATE_NAME = "before_overdue.ftl";
	private static final String OVERDUE_TEMPLATE_NAME = "overdue.ftl";
	
	private Configuration cfg;
	
	@PostConstruct
	public void setUp() throws IOException {
		this.cfg = new Configuration(Configuration.VERSION_2_3_22);
		cfg.setClassForTemplateLoading(this.getClass(), "/templates/");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	private Template getTemplate(String templateName) {
		try {
			Template template = cfg.getTemplate(templateName);
			return template;
		} catch (Exception e) {
			throw new IllegalStateException(String.format("Unable to load template: %s", OVERDUE_TEMPLATE_NAME));
		}
	}
	
	public String getRentMessage() {
		return getMessage(RENT_TEMPLATE_NAME); 
	}
	
	public String getBeforeOverdueMessage() {
		return getMessage(BEFORE_OVERDUE_TEMPLATE_NAME); 
	}

	
	public String getOverdueMessage() {
		return getMessage(OVERDUE_TEMPLATE_NAME);
	}
	
	private String getMessage(String templateName) {
		try {
			Writer out = new StringWriter();
			Template template = getTemplate(templateName);
			template.process(new HashMap(), out);
			return out.toString();
		} catch (Exception e) {
			throw new IllegalStateException(String.format("Unable to prepare template content!"));
		}
	}
}
