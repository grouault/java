//Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 11/01/2005 14:18:26
//Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
//Decompiler options: packimports(3) 
//Source File Name:   Config.java

package fr.citesciences.ldap;

import java.io.*;
import java.net.*;
import java.util.*;

//Referenced classes of package lbe.common:
//         Common, Session

public class Config
{

 public Config()
 {
 }

 public static String getAutoconnectSession()
 {
     return getP("autoload");
 }

 public static String getBaseLocation()
 {
     return baseLocation;
 }

 public static String getBaseLocationProperty()
 {
     return getP("base");
 }

 public static String getCACertFile()
 {
     return getP("cacert.file", "lbecacerts");
 }

 public static String getCACertPwd()
 {
     return getP("cacert.pwd", "changeit");
 }

 public static int getFixLocation()
 {
     return toInt("fixlocation", 0);
 }

 public static int getLogSize()
 {
     return toInt("logsize", 2048);
 }

 private static String getP(String type)
 {
     if(properties == null)
         return null;
     else
         return properties.getProperty(type);
 }

 private static String getP(String key, String defValue)
 {
     if(properties == null)
     {
         return defValue;
     } else
     {
         String vl = properties.getProperty(key);
         return vl != null ? vl : defValue;
     }
 }

 public static String getSessionDirectory()
 {
     return getP("session.dir", ".");
 }

 public static String getSessionKeyStore()
 {
     return sessionKeyStore;
 }

 public static String getSessionKeyStorePwd()
 {
     return sessionKeyStorePwd;
 }

 private static boolean isNullOrEmpty(String tmp)
 {
     return tmp == null || tmp != null && tmp.length() == 0;
 }

 public static void load(File file)
     throws IOException
 {
     BufferedReader r = null;
     try
     {
         r = new BufferedReader(new FileReader(file));
         properties = Session.readProperties(r);
     }
     finally
     {
         if(r != null)
             r.close();
     }
 }

 public static void load(URL url)
     throws IOException
 {
     BufferedReader r = null;
     try
     {
         URLConnection con = url.openConnection();
         InputStreamReader input = new InputStreamReader(con.getInputStream());
         r = new BufferedReader(input);
         properties = Session.readProperties(r);
     }
     finally
     {
         if(r != null)
             r.close();
     }
 }

 public static boolean popupErrorWindow()
 {
     return yesOrNo("popuperrorwindow");
 }

 public static void printSettings()
 {
     if(properties == null)
     {
         return;
     } else
     {
         System.out.println("Current Settings:");
         System.out.println(properties.toString());
         return;
     }
 }

 public void save(File file)
     throws IOException
 {
     PrintWriter d = null;
     try
     {
         d = new PrintWriter(new FileOutputStream(file));
         save(d);
     }
     finally
     {
         if(d != null)
             d.close();
     }
 }

 public void save(PrintWriter d)
     throws IOException
 {
     if(properties == null)
         return;
     Enumeration enume = properties.keys();
     
     int pos = 0;
     String key;
     String value;
     for(; enume.hasMoreElements(); d.println(key + "=" + value))
     {
         key = (String)enume.nextElement();
         value = (String)properties.get(key);
         pos = value.indexOf('\\');
         if(pos != -1)
             value = Session.escapeValue(pos, value);
     }

 }

 public void save(String file)
     throws Exception
 {
     PrintWriter d = null;
     try
     {
         d = Common.writeFile(file);
         save(d);
     }
     finally
     {
         if(d != null)
             d.close();
     }
 }

 public static void setBaseLocation(String loc)
     throws MalformedURLException
 {
     URL u = new URL(loc);
     String file = u.getFile();
     if(file == null || file.length() == 0)
         throw new MalformedURLException("The base location url does not specify a directory: " + loc);
     char last = file.charAt(file.length() - 1);
     if(last == '/' || last == '\\')
         baseLocation = loc;
     else
         throw new MalformedURLException("The base location url must specify a directory: " + loc);
 }

 public static void setBaseLocation(URL url)
 {
     baseLocation = url.toString();
 }

 public static void setSessionKeyStore(String store)
 {
     sessionKeyStore = store;
 }

 public static void setSessionKeyStorePwd(String pwd)
 {
     sessionKeyStorePwd = pwd;
 }

 private static int toInt(String label, int defValue)
 {
     String tmp = getP(label);
     if(isNullOrEmpty(tmp))
     {
         return defValue;
     } else
     {
         Integer intg = new Integer(tmp);
         return intg.intValue();
     }
 }

 private static boolean yesOrNo(String key)
 {
     String tmp = getP(key);
     return tmp != null && tmp.equalsIgnoreCase("yes");
 }

 public static final String LBE_DIR = ".lbe";
 public static final String DEF_CONFIG_FILE = "lbe.properties";
 private static String baseLocation = null;
 private static Properties properties = null;
 private static String sessionKeyStore = null;
 private static String sessionKeyStorePwd = null;

}