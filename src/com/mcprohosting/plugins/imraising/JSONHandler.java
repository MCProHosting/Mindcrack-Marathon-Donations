package com.mcprohosting.plugins.imraising;

import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;


public class JSONHandler {
	public static JSONObject readJsonFromUrl(String url) throws IOException {
		InputStream is = new URL(url).openStream();

		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String originalText = readAll(rd);
			int indexOfJsonStart = originalText.indexOf('(');
			String parsedText = originalText.substring(indexOfJsonStart+1, originalText.length()-1);
			
			JSONObject json = new JSONObject(parsedText);
			return json;
		} finally {
			is.close();
		}
	}
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		
		return sb.toString();
	}
}