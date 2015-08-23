package pl.sii.library.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static Date addDays(Date startDate, int ammount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.DATE, ammount);
		Date endDate = cal.getTime();
		return endDate;
	}
	
	public static Date subtractDays(Date startDate, int ammount) {
		return addDays(startDate, -ammount);
	}
	
}
