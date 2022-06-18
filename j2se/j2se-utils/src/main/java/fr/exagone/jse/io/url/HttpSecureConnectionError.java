package fr.exagone.jse.io.url;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class HttpSecureConnectionError {

  private HttpsURLConnection conn;
  
  private static String URL_TO_CHECK = "https://swet-tuf.pomona-fr.grp/swet-tuf/opc/batch/launch/";
  
  public static void main(String[] args) throws Exception {
    
    HttpSecureConnectionError httpSecureConn = new HttpSecureConnectionError();
    httpSecureConn.sendPost(URL_TO_CHECK);
    
  }
  
  private void sendPost(String url) throws Exception {
    
    URL obj = new URL(url);
    conn = (HttpsURLConnection) obj.openConnection();
    
    conn.setUseCaches(false);
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    String postParams = getFormParams();
    conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));
    
    conn.setDoOutput(true);
    conn.setDoInput(true);

    // Send post request
    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
    wr.writeBytes(postParams);
    wr.flush();
    wr.close();

    int responseCode = conn.getResponseCode();
    System.out.println("\nSending 'POST' request to URL : " + url);
    System.out.println("Post parameters : " + postParams);
    System.out.println("Response Code : " + responseCode);

    BufferedReader in = 
             new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
    }
    in.close();
    
  }
  
  private String getFormParams() throws UnsupportedEncodingException {

    List<String> paramList = new ArrayList<String>();
    paramList.add("login=" + URLEncoder.encode("opcon", "UTF-8"));
    paramList.add("password=" + URLEncoder.encode("672795a51017863978e1a189ef0c492ca76347e349acbe403f919e27df958bdfa96acbf7dbb43496dca514c2827591c9", "UTF-8"));
    paramList.add("code=" + URLEncoder.encode("cache-maintenance", "UTF-8"));
    
    
  
    // build parameters list
    StringBuilder result = new StringBuilder();
    for (String param : paramList) {
        if (result.length() == 0) {
            result.append(param);
        } else {
            result.append("&" + param);
        }
    }
    return result.toString();
    
  }
  
}
