package edu.nccu.floodfire.util;

public class StringUtil {
	
	public static String unicodeSurrogate(String text)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
		    char ch = text.charAt(i);
		    if (!Character.isHighSurrogate(ch) && !Character.isLowSurrogate(ch)) {
		        sb.append(ch);
		    }
		}
		return sb.toString();
	}

}
