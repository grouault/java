package fr.exagone.jse.io.url;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class HttpSecureConnection {

  private HttpsURLConnection conn;
  
  private static String URL_TO_CHECK = "https://swet-tuf.pomona-fr.grp/swet-tuf/opc/batch/launch/";
  
  private static String KEY_STORE_PATH = "C:\\Services\\devs\\curl\\certificat\\cacerts"; 
  
  public static void main(String[] args) throws Exception {
    
    HttpSecureConnection httpSecureConn = new HttpSecureConnection();
    httpSecureConn.sendPost(URL_TO_CHECK);
    
  }
  
  private void sendPost(String url) throws Exception {
    
    
    /*
    InputStream ins = new FileInputStream(KEY_STORE_PATH);
    KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
    keyStore.load(ins, "changeit".toCharArray());
    ins.close();
    
    
    SSLContext context = SSLContext.getInstance("TLS");
    TrustManagerFactory tmf =
        TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    tmf.init(keyStore);
    X509TrustManager defaultTrustManager = (X509TrustManager)tmf.getTrustManagers()[0];
    SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
    context.init(null, new TrustManager[] {tm}, null);
    SSLSocketFactory sslSocketFactory = context.getSocketFactory();
    
  
    SSLContext ctx = SSLContext.getInstance("TLS");
    KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    kmf.init(keyStore, "changeit".toCharArray());
    ctx.init(kmf.getKeyManagers(), null, null);
    SSLSocketFactory sslSocketFactory = ctx.getSocketFactory();
    
    HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
    */
    
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
  
  
  private static class SavingTrustManager implements X509TrustManager {

    private final X509TrustManager tm;
    private X509Certificate[] chain;
    
    SavingTrustManager(X509TrustManager tm) {
        this.tm = tm;
    }
    
    @Override
    public X509Certificate[] getAcceptedIssuers() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType)
      throws CertificateException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType)
      throws CertificateException {
        this.chain = chain;
        tm.checkServerTrusted(chain, authType);
}
  }
    
}


