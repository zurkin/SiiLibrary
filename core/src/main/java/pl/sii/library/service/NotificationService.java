package pl.sii.library.service;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import pl.sii.library.application.BookExpiredQuery;
import pl.sii.library.domain.persistence.Book;
import pl.sii.library.domain.persistence.Customer;

@Singleton
public class NotificationService {

	@Inject
	private BookExpiredQuery bookExpiredQuery;
	@Inject
	private MailService mailSerice;
	@Inject
	private TemplateService templateService;
	
	@Schedule(hour="17", persistent=false)
	public void sendNotificationsWeekOverdue() {
		String message = templateService.getOverdueMessage();
		List<Book> expiredBooks = bookExpiredQuery.checkForExpiredAfterWeek();
		for (Book book : expiredBooks) {
			Customer customer = book.getRent().getCustomer();
			String email = customer.getEmail();
			mailSerice.sendMessage("Overdue", message, email);
		}
	}

	@Schedule(hour="16", persistent=false)
	public void sendNotifications3DaysBeforeOverdue() {
		String message = templateService.getBeforeOverdueMessage();
		List<Book> expiredBooks = bookExpiredQuery.checkForExpiredIn3Days();
		for (Book book : expiredBooks) {
			Customer customer = book.getRent().getCustomer();
			String email = customer.getEmail();
			mailSerice.sendMessage("Overdue", message, email);
		}
	}
	
}
