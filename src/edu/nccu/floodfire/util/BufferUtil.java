package edu.nccu.floodfire.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class BufferUtil {
	public static ByteBuffer toByteBuffer(String value) throws UnsupportedEncodingException
	{
		return ByteBuffer.wrap(value.getBytes("UTF-8"));
	}
	
	public static String toString(ByteBuffer buffer)
			throws UnsupportedEncodingException {
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes);
		return new String(bytes, "UTF-8");
	}

}
