package pl.sii.library.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailMessageBO extends MimeMessage {

	public MailMessageBO(Session session) {
		super(session);
	}

	public static class MailMessageBOBuilder {
		
		private Session session;
		private InternetAddress from;
		private String subject;
		private String body;
		private List<InternetAddress> recipients = new ArrayList<InternetAddress>();
		
		public MailMessageBOBuilder(Session session) {
			this.session = session;
		}

		public MailMessageBOBuilder withFrom(String from) throws AddressException {
			this.from = new InternetAddress(from);
			return this;
		}
		
		public MailMessageBOBuilder withSubject(String subject) {
			this.subject = subject;
			return this;
		}

		public MailMessageBOBuilder withBody(String body) {
			this.body = body;
			return this;
		}

		public MailMessageBOBuilder withRecipients(String ... recipients) throws AddressException {
			for (String recipient : recipients) {
				InternetAddress address = new InternetAddress(recipient);
				this.recipients.add(address);
			}
			return this;
		}
		
		public MailMessageBO build() throws AddressException, MessagingException {
			MailMessageBO mailMessageBO = new MailMessageBO(this.session);
			mailMessageBO.setFrom(this.from);
			mailMessageBO.setSubject(this.subject);
			mailMessageBO.setText(this.body);
			for (InternetAddress recipient : this.recipients) {
				mailMessageBO.addRecipient(Message.RecipientType.TO, recipient);
			}
			
			return mailMessageBO;
		}

	}
	
}
