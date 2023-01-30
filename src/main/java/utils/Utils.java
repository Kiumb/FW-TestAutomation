package utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Utils {
	
	
	public String getTime () {
		String timeStr;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timeStr = sdf.format(timestamp);
		
	}
	
	public static String getTimeForName () {
		String timeStr;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timeStr = sdf.format(timestamp);
		
	}

}
