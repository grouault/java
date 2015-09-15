//Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 11/01/2005 14:18:26
//Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
//Decompiler options: packimports(3) 
//Source File Name:   Session.java

package fr.citesciences.ldap;

import java.io.*;
import java.util.*;

//Referenced classes of package lbe.common:
//         Common, Debug

public class Session
{

 public Session()
 {
     properties = null;
 }

 public Session(File file)
     throws IOException
 {
     properties = null;
     load(file);
 }

 public Session(String file)
     throws Exception
 {
     properties = null;
     load(file);
 }

 public Session(Properties props)
 {
     properties = null;
     properties = props;
 }

 public boolean areAttributesSet()
 {
     return get("ldap.attributes.list") != null;
 }

 public boolean autoconnect()
 {
     return yesOrNo("autoconnect");
 }

 public void clear()
 {
     if(properties != null)
         properties.clear();
 }

 public boolean deleteOldDN()
 {
     return yesOrNo("deleteolddn");
 }

 public static String escapeValue(int pos, String value)
 {
     int len = value.length();
     StringBuffer buf = new StringBuffer(len + 1);
     for(int x = 0; x < len;)
     {
         char ch = value.charAt(x++);
         if(ch == '\\')
         {
             buf.append(ch);
             ch = value.charAt(x++);
             if(ch != '\\')
                 buf.append('\\');
             buf.append(ch);
         } else
         {
             buf.append(ch);
         }
     }

     return buf.toString();
 }

 private String get(String type)
 {
     if(properties == null)
         return null;
     else
         return properties.getProperty(type);
 }

 private String get(String key, String defValue)
 {
     if(properties == null)
     {
         return null;
     } else
     {
         String vl = properties.getProperty(key);
         return vl != null ? vl : defValue;
     }
 }

 public String getAliasingOption()
 {
     return get("derefaliases", "always");
 }

 public String[] getAttributesList()
 {
     String list = get("ldap.attributes.list");
     if(list == null)
         return null;
     StringTokenizer tokens = new StringTokenizer(list, " ");
     String at[] = new String[tokens.countTokens()];
     for(int i = 0; tokens.hasMoreTokens(); i++)
         at[i] = tokens.nextToken();

     return at;
 }

 public String getBaseDN()
 {
     return get("basedn", "");
 }

 public int getBatchSize()
 {
     return toInt("batchsize", 0);
 }

 public static Session getDefaultSession()
 {
     Properties p = new Properties();
     p.put("host", "ldap.itd.umich.edu");
     p.put("version", "2");
     p.put("port", "389");
     p.put("basedn", "o=University of Michigan, c=us");
     p.put("managerdn", "cn=Directory Manager");
     return new Session(p);
 }

 public String getHost()
 {
     return get("host");
 }

 public String getJndiProvider()
 {
     return get("jndi.provider");
 }

 public String getKeyStore()
 {
     return get("keystore");
 }

 public String getKeyStorePwd()
 {
     return get("passphrase");
 }

 public String getLdapSocketFactory()
 {
     return get("ldapsocketfactory", "lbe.ssl.AdvancedSocketFactory");
 }

 public String getLeafIndicator()
 {
     return get("leafindicator");
 }

 public int getLeafIndicatorType()
 {
     String type = get("leafindicatortype");
     if(type == null)
         return -1;
     if(type.equalsIgnoreCase("int"))
         return 0;
     return !type.equalsIgnoreCase("boolean") ? -1 : 1;
 }

 public int getLimit()
 {
     return toInt("limit", 0);
 }

 public String getListFilter()
 {
     return get("ldap.list.filter");
 }

 public String getManagerDN()
 {
     return get("managerdn");
 }

 public String getPassword()
 {
     return get("password");
 }

 public String getPort()
 {
     if(useSSL())
         return get("sslport", "636");
     else
         return get("port", "389");
 }

 public Properties getProperties()
 {
     return properties;
 }

 public String getSaslClientPkgs()
 {
     return get("javax.security.sasl.client.pkgs");
 }

 public String getSecurityAuthentication()
 {
     return get("security.authentication", "simple");
 }

 public String getSecurityProtocol()
 {
     return get("security.protocol");
 }

 public int getTableSortOption()
 {
     String sort = get("sorttable");
     if(sort == null)
         return 0;
     if(sort.equalsIgnoreCase("value:ascending"))
         return 1;
     if(sort.equalsIgnoreCase("value:descending"))
         return 2;
     if(sort.equalsIgnoreCase("ascending") || sort.equalsIgnoreCase("attribute:ascending"))
         return 3;
     return !sort.equalsIgnoreCase("descending") && !sort.equalsIgnoreCase("attribute:descending") ? 0 : 4;
 }

 public int getTimeout()
 {
     return toInt("timeout", 0);
 }

 public int getTreeSortOption()
 {
     String sort = get("sorttree");
     if(sort == null)
         return 0;
     if(sort.equalsIgnoreCase("ascending"))
         return 1;
     return !sort.equalsIgnoreCase("descending") ? 0 : 2;
 }

 public int getVersion()
 {
     return toInt("version", 2);
 }

 public void load(File file)
     throws IOException
 {
     BufferedReader r = null;
     try
     {
         r = new BufferedReader(new FileReader(file));
         properties = readProperties(r);
     }
     finally
     {
         if(r != null)
             r.close();
     }
 }

 public void load(String file)
     throws Exception
 {
     BufferedReader r = null;
     try
     {
         r = Common.openFile(file);
         properties = readProperties(r);
     }
     finally
     {
         if(r != null)
             r.close();
     }
 }

 public boolean manageReferrals()
 {
     return yesOrNo("managereferrals");
 }

 public static Properties readProperties(BufferedReader r)
     throws IOException
 {
     Properties p = null;
     String line = null;
     String attribute = null;
     String value = null;
     int pos = 0;
     while((line = r.readLine()) != null) 
     {
         line = line.trim();
         if(line.length() != 0 && !line.startsWith("#"))
         {
             pos = line.indexOf("=");
             if(pos == -1)
             {
                 Debug.error("Invalid line in properties file: " + line);
             } else
             {
                 attribute = line.substring(0, pos).toLowerCase().trim();
                 value = line.substring(pos + 1).trim();
                 value = unescapeValue(value);
                 if(p == null)
                     p = new Properties();
                 p.put(attribute, value);
             }
         }
     }
     return p;
 }

 public void save(File file)
     throws Exception
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
     throws Exception
 {
     if(properties == null)
         return;
     Enumeration enume = properties.keys();
     d.println(COMMENT);
     int pos = 0;
     String key;
     String value;
     for(; enume.hasMoreElements(); d.println(key + "=" + value))
     {
         key = (String)enume.nextElement();
         value = (String)properties.get(key);
         pos = value.indexOf('\\');
         if(pos != -1)
             value = escapeValue(pos, value);
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

 public void setProperties(Properties props)
 {
     properties = props;
 }

 public void setUserBind(boolean userBind)
 {
     if(properties == null)
     {
         return;
     } else
     {
         properties.put("managerlogin", userBind ? "yes" : "no");
         return;
     }
 }

 public void setUserPassword(String pwd)
 {
     if(properties == null || pwd == null)
     {
         return;
     } else
     {
         properties.put("password", pwd);
         return;
     }
 }

 public boolean supportsMoveTree()
 {
     return yesOrNo("supportsmovetree");
 }

 private int toInt(String label, int defValue)
 {
     String tmp = get(label);
     if(Common.isNullOrEmpty(tmp))
     {
         return defValue;
     } else
     {
         Integer intg = new Integer(tmp);
         return intg.intValue();
     }
 }

 public static String unescapeValue(String value)
 {
     int len = value.length();
     StringBuffer buf = new StringBuffer(len);
     for(int x = 0; x < len;)
     {
         char ch = value.charAt(x++);
         if(ch == '\\')
         {
             ch = value.charAt(x++);
             buf.append(ch);
         } else
         {
             buf.append(ch);
         }
     }

     return buf.toString();
 }

 public boolean useSSL()
 {
     String vl = getSecurityProtocol();
     return vl != null && vl.equalsIgnoreCase("ssl");
 }

 public boolean userBind()
 {
     return yesOrNo("managerlogin");
 }

 private boolean yesOrNo(String key)
 {
     String tmp = get(key);
     return tmp != null && tmp.equalsIgnoreCase("yes");
 }

 public static final String EXT = ".cfg";
 public static final String DEF_SESSION_FILE = "browser.cfg";
 private static final String lineSeparator = System.getProperty("line.separator");
 private static final String COMMENT = "#################################" + lineSeparator + "# LDAP Browser v2.8 config file #" + lineSeparator + "#################################" + lineSeparator;
 private Properties properties;

}