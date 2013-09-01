package edu.nccu.floodfire.util;

import java.io.FileWriter;

public class FileWriterUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileWriterUtil fu = new FileWriterUtil();
		fu.write();
	}
	
	public void write()
	{
		try {
			FileWriter fileWriter =                 
				new FileWriter("temp.txt"); 
			fileWriter.write(System.currentTimeMillis()+""); 
			System.out.println("write");
			fileWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
