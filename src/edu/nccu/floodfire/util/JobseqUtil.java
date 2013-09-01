package edu.nccu.floodfire.util;

public class JobseqUtil {
	public static InheritableThreadLocal<String> searchlocal = new InheritableThreadLocal<String>();
	public static InheritableThreadLocal<String> streamlocal = new InheritableThreadLocal<String>();

	public static String getSearchJobseq(){
		return searchlocal.get();
	}

	public static void setSearchJobseq(String jobseq){ 
		searchlocal.set(jobseq);
	} 
	public static void removeSearchlocalJobseq(){ 
		searchlocal.remove();
	} 
	
	public static String getStreamJobseq(){
		return streamlocal.get();
	}

	public static void setStreamJobseq(String jobseq){ 
		streamlocal.set(jobseq);
	} 
	public static void removeStreamlocalJobseq(){ 
		streamlocal.remove();
	} 

}
