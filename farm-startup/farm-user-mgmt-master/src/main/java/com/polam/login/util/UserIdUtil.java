package com.polam.login.util;

import java.text.MessageFormat;
import java.util.Date;

import com.polam.login.exception.UserCreationException;

public class UserIdUtil {
	
	private static final String PATTERN = "{0}{1}{2}";

	public static String createTemporaryUserId(String name) throws UserCreationException{
		name = (name.length()>5)? name.substring(0,4): name;
		Date date = new Date();
		String milliseconds = Long.toString(date.getTime());
		String random = Long.toString(getRandomIntBetweenRange(0, 100));
		return MessageFormat.format(PATTERN, name.toUpperCase(), random, milliseconds);
	}
	
	private static long getRandomIntBetweenRange(long min, long max){
		return (long) ((Math.random()*((max-min)+1))+min);
	}
}
