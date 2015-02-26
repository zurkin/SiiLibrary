package pl.sii.core.service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

/**
 * Created by a050600 on 2015-02-26.
 */
public class PhraseBuilder {

	private Map<String, String> templates;

	public String buildPhrase(String id, Object... args) {
		return MessageFormat.format(templates.get(id), args);
	}

	@PostConstruct
	public void initialize() {
		templates = new HashMap<String, String>();
		templates.put("hello", "Hello, {0}!");
	}
}
