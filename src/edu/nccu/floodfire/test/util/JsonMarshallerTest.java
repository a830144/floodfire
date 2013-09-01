package edu.nccu.floodfire.test.util;

import java.io.UnsupportedEncodingException;

import edu.nccu.floodfire.model.twitter.TweetData;
import edu.nccu.floodfire.util.JsonMarshaller;

public class JsonMarshallerTest {
	
	public static void main(String[] args) {
		
		
		
		TweetData td = new TweetData();
		td.setCoordinates("xxxxx");
		td.setEntities("ddddd");
		try {
			JsonMarshaller.marshallStatus(td);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

}
