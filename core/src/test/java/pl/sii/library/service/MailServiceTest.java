package pl.sii.library.service;

import org.junit.Test;

public class MailServiceTest {


	@Test
	public void test() {
		//given
		MailService mailService = new MailService();
		mailService.setUp();
		//when
		mailService.sendMessage("Test", "Zażółć gęślą jaźń!", "holdanowicz@gmail.com");
		
		//then
	}
	
}
