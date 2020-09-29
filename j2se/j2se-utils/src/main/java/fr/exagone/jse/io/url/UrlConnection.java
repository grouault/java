package fr.exagone.jse.io.url;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class UrlConnection {
	
	public static void main(String[] args) {
		try {
			URL url = new URL("http://www.google.fr");
			URLConnection urlConnect = url.openConnection();
			Map mapField = urlConnect.getHeaderFields();
			String statusCode = urlConnect.getHeaderField(0);
			System.out.println(statusCode);
			if (statusCode.contains("200 OK")){
				System.out.println("connection ok");
			}
			else{
				System.out.println("connection ko");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
