//Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 12/01/2005 17:40:43
//Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
//Decompiler options: packimports(3) 
//Source File Name:   AdvancedSocketFactory.java

package fr.citesciences.ldap;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.Security;
import java.util.Hashtable;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.sun.net.ssl.KeyManagerFactory;
import com.sun.net.ssl.SSLContext;
import com.sun.net.ssl.TrustManager;
import com.sun.net.ssl.internal.ssl.Provider;


//Referenced classes of package lbe.ssl:
//         Truster

public class AdvancedSocketFactory extends SSLSocketFactory
{

 protected AdvancedSocketFactory()
 {
     factory = null;
     init(null, null);
 }

 protected AdvancedSocketFactory(InputStream in, String keyStore, String password)
     throws Exception
 {
     factory = null;
     KeyStore ks = null;
     if(keyStore.endsWith(".p12"))
         ks = KeyStore.getInstance("PKCS12");
     else
         ks = KeyStore.getInstance("JKS");
     char pwd[] = password.toCharArray();
     ks.load(in, pwd);
     init(ks, pwd);
 }

 protected AdvancedSocketFactory(String keyStore, String passphrase)
 {
     factory = null;
     init(null, null);
 }

 private static void closeStream(InputStream in)
 {
     if(in == null)
         return;
     try
     {
         in.close();
     }
     catch(Exception _ex) { }
 }

 public Socket createSocket(String host, int port)
     throws IOException, UnknownHostException
 {
     return factory.createSocket(host, port);
 }

 public Socket createSocket(String host, int port, InetAddress client_host, int client_port)
     throws IOException, UnknownHostException
 {
     return factory.createSocket(host, port, client_host, client_port);
 }

 public Socket createSocket(InetAddress host, int port)
     throws IOException, UnknownHostException
 {
     return factory.createSocket(host, port);
 }

 public Socket createSocket(InetAddress host, int port, InetAddress client_host, int client_port)
     throws IOException, UnknownHostException
 {
     return factory.createSocket(host, port, client_host, client_port);
 }

 public Socket createSocket(Socket socket, String host, int port, boolean autoclose)
     throws IOException, UnknownHostException
 {
     return factory.createSocket(socket, host, port, autoclose);
 }

 public static synchronized SocketFactory getDefault()
 {
     String keyStore = Config.getSessionKeyStore();
     if(keyStore == null)
         return getDefaultFactory();
     if(factories == null)
         factories = new Hashtable();
     SocketFactory fac = (SocketFactory)factories.get(keyStore);
     if(fac == null)
     {
         fac = load(keyStore, Config.getSessionKeyStorePwd());
         if(fac != null)
             factories.put(keyStore, fac);
     }
     return fac;
 }

 public String[] getDefaultCipherSuites()
 {
     return factory.getDefaultCipherSuites();
 }

 private static SocketFactory getDefaultFactory()
 {
     if(default_factory == null)
         default_factory = new AdvancedSocketFactory();
     return default_factory;
 }

 private TrustManager[] getDefaultTrustManager()
 {
     if(trustManagers == null)
         trustManagers = (new Truster[] {
             new Truster()
         });
     return trustManagers;
 }

 private static String getPassword(String keyStore)
 {
     javax.swing.JTextField field = new JPasswordField(20);
     Object comp[] = new Object[3];
     comp[0] = "Keystore: " + keyStore;
     comp[1] = "Enter password: ";
     comp[2] = field;
     Object options[] = {
         Intr.get("ok.button.lb"), Intr.get("cancel.button.lb")
     };
     int rs = JOptionPane.showOptionDialog(null, ((Object) (comp)), "Keystore Password", -1, 3, null, options, options[0]);
     if(rs != 0)
         return null;
     else
         return field.getText();
 }

 public String[] getSupportedCipherSuites()
 {
     return factory.getSupportedCipherSuites();
 }

 private void init(KeyStore ks, char password[])
 {
     SSLContext ctx = null;
     com.sun.net.ssl.KeyManager keyManagers[] = null;
     TrustManager trustManagers[] = null;
     try
     {
         if(ks != null)
         {
             KeyManagerFactory kmf = null;
             kmf = KeyManagerFactory.getInstance("SunX509");
             kmf.init(ks, password);
             keyManagers = kmf.getKeyManagers();
         }
         ctx = SSLContext.getInstance("TLS");
         trustManagers = getDefaultTrustManager();
         ctx.init(keyManagers, trustManagers, null);
         factory = ctx.getSocketFactory();
     }
     catch(Exception e)
     {
         System.err.println("ASF Error: failed to initialize : " + e.getMessage());
         if(Debug.asfDebug())
             e.printStackTrace();
     }
 }

 private static SocketFactory load(String keyStore, String pwd)
 {
     InputStream in = null;
     try
     {
         in = new FileInputStream(keyStore);
     }
     catch(IOException e)
     {
         String msg = "Unable to open the user key store:\n" + keyStore + "\nError: " + e.getMessage();
         JOptionPane.showMessageDialog(null, msg, "Error", 0);
         return null;
     }
     if(pwd == null)
     {
         pwd = getPassword(keyStore);
         if(pwd == null)
         {
             closeStream(in);
             return null;
         }
     }
     try
     {
         SocketFactory socketfactory;
         try
         {
             AdvancedSocketFactory advancedsocketfactory = new AdvancedSocketFactory(in, keyStore, pwd);
             return advancedsocketfactory;
         }
         catch(Exception e)
         {
             String msg = "Unable to load the user key store:\n" + keyStore + "\nError: " + e.getMessage();
             JOptionPane.showMessageDialog(null, msg, "Error", 0);
             socketfactory = null;
         }
         return socketfactory;
     }
     finally
     {
         closeStream(in);
     }
 }

 private SSLSocketFactory factory;
 private static TrustManager trustManagers[] = null;
 private static AdvancedSocketFactory default_factory = null;
 private static Hashtable factories = null;

 static 
 {
     Security.addProvider(new Provider());
 }
}