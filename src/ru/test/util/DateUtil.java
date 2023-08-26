package ru.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static ru.test.util.Message.msg;

public class DateUtil {
	public static final SimpleDateFormat DATE_TIME = new SimpleDateFormat("dd.MM.yy HH:mm");
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");
	
	public static Date getData(String dateStr,String time){
		String dateTimeStr = dateStr +" "+time;
		Date date = null;
        if (dateStr==null || dateStr.isEmpty()){
        	msg("Null or Empty date!");
            return date;
        }
        
        try {
            date = DATE_TIME.parse(dateTimeStr);
        } catch (ParseException e) {
            msg("Cannot get parser date from " + dateTimeStr);
        }
        return date;
    }
	public static ZonedDateTime getData(String dateStr,String time,String tmz){
		String dateTimeStr = dateStr +" "+time;
		ZoneId timeZone = ZoneId.of(tmz);
		LocalDateTime date = null;
	  if (dateStr==null || dateStr.isEmpty()){
        	msg("Null or Empty date!");
            return null;
        }
        
        date = LocalDateTime.parse(dateTimeStr,formatter);
		ZonedDateTime dateTmz = date.atZone(timeZone);
        return dateTmz;
    }
}
