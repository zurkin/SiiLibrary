package pl.sii.library.domain.persistence;

import javax.persistence.Embeddable;

@Embeddable
public class Customer {

	private String nick;
	private String email;

	public Customer() {
		super();
	}

	public Customer(String nick, String email) {
		super();
		this.nick = nick;
		this.email = email;
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
