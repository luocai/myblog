package util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static String getMonth(Date date){
		
		String[] Mon = {"一","二","三","四","五","六","七","八","九","十","十一","十二"};
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		int mon = calendar.get(Calendar.MONTH);
		
		return Mon[mon];
	}
	
	public static Integer getDay(Date date){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public static Integer getYear(Date date){
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

}
