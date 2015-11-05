package pl.sii.library.domain.persistence;

import javax.persistence.Embeddable;

@Embeddable
public class Customer {

	private static final String EMAIL_SUFFIX = "@pl.sii.eu";
	
	private String nick;
	private String email;

	public Customer() {
		super();
	}

	public Customer(String nick) {
		super();
		this.nick = nick;
		this.email = nick.concat(EMAIL_SUFFIX);
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
