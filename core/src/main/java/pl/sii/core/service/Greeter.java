package pl.sii.core.service;

import java.io.PrintStream;

import javax.inject.Inject;

/**
 * Created by a050600 on 2015-02-26.
 */
public class Greeter {

	private PhraseBuilder phraseBuilder;

	@Inject
	public Greeter(PhraseBuilder phraseBuilder) {
		this.phraseBuilder = phraseBuilder;
	}

	public void greet(PrintStream to, String name) {
		to.println(createGreeting(name));
	}

	public String createGreeting(String name) {
		return "Hello, " + name + "!";
	}
}
