//Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 12/01/2005 17:47:06
//Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
//Decompiler options: packimports(3) 
//Source File Name:   Truster.java

package fr.citesciences.ldap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import com.sun.net.ssl.TrustManagerFactory;
import com.sun.net.ssl.X509TrustManager;

//Referenced classes of package lbe.ssl:
//         CAVerifier, GUICAVerifier

public class Truster
 implements X509TrustManager
{

 public Truster()
 {
     certStore = null;
     certStorePwd = null;
     trustManager = null;
     ks = null;
     verifier = null;
     certStore = Config.getCACertFile();
     certStorePwd = Config.getCACertPwd().toCharArray();
     init();
     verifier = new GUICAVerifier();
 }

 private boolean deleteCert(String id)
 {
     try
     {
         ks.deleteEntry(id);
     }
     catch(KeyStoreException _ex)
     {
         return false;
     }
     return true;
 }

 public X509Certificate[] getAcceptedIssuers()
 {
     if(trustManager == null)
         return null;
     else
         return trustManager.getAcceptedIssuers();
 }

 private X509Certificate getCACert(X509Certificate chain[])
 {
     X509Certificate ca = chain[chain.length - 1];
     if(ca.getSubjectDN().equals(ca.getIssuerDN()))
         return ca;
     else
         return null;
 }

 private void init()
 {
     try
     {
         if(certStore.endsWith(".p12"))
             ks = KeyStore.getInstance("PKCS12");
         else
             ks = KeyStore.getInstance("JKS");
     }
     catch(KeyStoreException e)
     {
         System.err.println("ASF Truster: Failed to create cert store : " + e.getMessage());
         return;
     }
     InputStream in = null;
     if(certStore.indexOf("://") == -1)
         try
         {
             in = new FileInputStream(certStore);
         }
         catch(FileNotFoundException _ex) { }
     else
         try
         {
             URL url = new URL(certStore);
             URLConnection con = url.openConnection();
             in = con.getInputStream();
         }
         catch(MalformedURLException e)
         {
             System.err.println("ASF Truster: The location of the cert store file is invalid: " + e.getMessage());
         }
         catch(IOException _ex) { }
     try
     {
         ks.load(in, certStorePwd);
     }
     catch(Exception e)
     {
         System.err.println("ASF Truster: Failed to load the cert store : " + e.getMessage());
         return;
     }
     finally
     {
         if(in != null)
             try
             {
                 in.close();
             }
             catch(Exception _ex) { }
     }
     try
     {
         trustManager = initTrustManager(ks);
     }
     catch(Exception e)
     {
         System.err.println("ASF Truster: Failed to create initial trust manager : " + e.getMessage());
         return;
     }
 }

 private X509TrustManager initTrustManager(KeyStore ks)
     throws NoSuchAlgorithmException, KeyStoreException
 {
     TrustManagerFactory trustManagerFactory = null;
     trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
     trustManagerFactory.init(ks);
     com.sun.net.ssl.TrustManager trusts[] = trustManagerFactory.getTrustManagers();
     return (X509TrustManager)trusts[0];
 }

 private boolean isAccepted(X509Certificate caCert)
 {
     X509Certificate certs[] = getAcceptedIssuers();
     if(certs == null)
         return false;
     for(int i = 0; i < certs.length; i++)
         if(caCert.equals(certs[i]))
             return true;

     return false;
 }

 public boolean isClientTrusted(X509Certificate chain[])
 {
     if(trustManager == null)
         return false;
     else
         return trustManager.isClientTrusted(chain);
 }

 public boolean isServerTrusted(X509Certificate chain[])
 {
     if(trustManager != null)
     {
         boolean rs = trustManager.isServerTrusted(chain);
         if(rs)
             return rs;
     }
     X509Certificate ca = getCACert(chain);
     if(ca != null)
     {
         if(isAccepted(ca))
         {
             verifier.chainVerifyFailed(2);
             return false;
         }
         String id = String.valueOf(System.currentTimeMillis());
         X509TrustManager tmpTrustManager = null;
         try
         {
             ks.setCertificateEntry(id, ca);
             tmpTrustManager = initTrustManager(ks);
         }
         catch(Exception e)
         {
             System.err.println("ASF Truster: Failed to create tmp trust store : " + e.getMessage());
             return false;
         }
         if(tmpTrustManager.isServerTrusted(chain))
         {
             switch(verifier.isTrusted(ca))
             {
             case 1: // '\001'
                 return false;

             case 2: // '\002'
                 saveStore();
                 trustManager = tmpTrustManager;
                 return true;

             case 3: // '\003'
                 deleteCert(id);
                 return true;
             }
             return false;
         } else
         {
             verifier.chainVerifyFailed(3);
             return false;
         }
     } else
     {
         verifier.chainVerifyFailed(1);
         return false;
     }
 }

 private boolean saveStore()
 {
     OutputStream out = null;
     try
     {
         try
         {
             if(certStore.indexOf("://") == -1)
             {
                 out = new FileOutputStream(certStore);
             } else
             {
                 URL url = new URL(certStore);
                 URLConnection con = url.openConnection();
                 con.setDoOutput(true);
                 out = con.getOutputStream();
             }
             ks.store(out, certStorePwd);
             boolean flag = true;
             return flag;
         }
         catch(Exception e)
         {
             System.err.println("ASF Truster: Failed to save trust store : " + e.getMessage());
         }
         boolean flag1 = false;
         return flag1;
     }
     finally
     {
         if(out != null)
             try
             {
                 out.close();
             }
             catch(Exception _ex) { }
     }
 }

 private String certStore;
 private char certStorePwd[];
 private X509TrustManager trustManager;
 private KeyStore ks;
 private CAVerifier verifier;
}