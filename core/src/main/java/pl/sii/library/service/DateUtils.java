package pl.sii.library.service;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static Date changeCurrentDateByDays(int days) {
		Calendar cal= Calendar.getInstance();
    	cal.add(Calendar.DATE, days);
    	Date compareDate = cal.getTime();
		return compareDate;
	}
	
}
