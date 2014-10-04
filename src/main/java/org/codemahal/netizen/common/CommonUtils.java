package org.codemahal.netizen.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class CommonUtils {
	static Logger LOG = Logger.getLogger(CommonUtils.class);

	public static String getContentAsText(InputStream in) throws IOException {
		LOG.info("Extracting the content from stream...");
		InputStreamReader inReader = new InputStreamReader(in);
		BufferedReader stream = new BufferedReader(inReader);
		StringBuilder theContent = new StringBuilder();
		String aLine = "";
		while ((aLine = stream.readLine()) != null) {
			theContent.append(aLine + "\n");
		}
		in.close();
		return theContent.toString();
	}

	public static String bytesToHigher(long bytes) {
		String toRet = "Unkown";
		if (bytes < 1024) {
			toRet = bytes + " B";
		} else if (bytes < (1024 * 1024)) {
			float val = bytes / (1024);
			toRet = val + " KB";
		} else if (bytes < (1024 * 1024 * 1024)) {
			float val = bytes / (1024 * 1024);
			toRet = val + " MB";
		} else if (bytes < (1024 * 1024 * 1024 * 1024)) {
			float val = bytes / (1024 * 1024 * 1024);
			toRet = val + " GB";
		}

		return toRet;
	}

	public static String getContentAsText(File input) {

		StringBuilder theContent = new StringBuilder();
		BufferedReader br = null;
		try {
			String temp;

			br = new BufferedReader(new FileReader(input.getAbsolutePath()));

			while ((temp = br.readLine()) != null) {
				theContent.append(temp + "\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return theContent.toString();
	}
}
