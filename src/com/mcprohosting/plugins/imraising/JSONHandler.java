package com.mcprohosting.plugins.imraising;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONObject;

public class JSONHandler {
	public static JSONObject readJsonFromUrl(String url) {
		InputStream inputStream = null;

		try {
			inputStream = new URL(url).openStream();

			BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			String originalText = readAll(rd);
			int indexOfJsonStart = originalText.indexOf('(');
			String parsedText = originalText.substring(indexOfJsonStart+1, originalText.length()-1);
			
			JSONObject json = new JSONObject(parsedText);
			return json;
		} catch (IOException e) {
			ImRaising.getPlugin().getLogger().severe("Failed to get/process data from " + url + ", error: " + e.getMessage());
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				ImRaising.getPlugin().getLogger().severe("Something has gone very wrong.");
				e.printStackTrace();
			}
		}

		return null;
	}

	public static boolean verifyURL(String url) {
		try {
			readJsonFromUrl(url);
			return true;
		} catch (Exception e) {
			return false;
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