package org.codemahal.netizen.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import org.apache.log4j.Logger;

public class ReadUtils {
	static Logger LOG = Logger.getLogger(ReadUtils.class);
	
	public static StringBuilder getContentAsText(InputStream in) throws IOException{
		LOG.info("Extracting the content from stream...");
		InputStreamReader inReader = new InputStreamReader(in);
		BufferedReader stream = new BufferedReader(inReader);
		StringBuilder theContent = new StringBuilder();
		String aLine ="";
		while((aLine = stream.readLine())!=null){
			theContent.append(aLine+"\n");
		}
		in.close();
		return theContent;
	}
	public static String bytesToHigher(long bytes){
		String toRet="Unkown";
		if(bytes < 1024){
			toRet = bytes + " B";
		}else if(bytes < (1024*1024)){
			float val = bytes/(1024);
			toRet = val +" KB";
		}else if(bytes < (1024*1024*1024)){
			float val = bytes/(1024*1024);
			toRet = val +" MB";
		}else if(bytes < (1024*1024*1024*1024)){
			float val = bytes/(1024*1024*1024);
			toRet = val +" GB";
		}
		
		return toRet;
	}

}
