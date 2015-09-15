//Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 11/01/2005 14:18:26
//Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
//Decompiler options: packimports(3) 
//Source File Name:   Debug.java

package fr.citesciences.ldap;

import java.util.StringTokenizer;

public class Debug
{

 public Debug()
 {
 }

 public static boolean asfDebug()
 {
     return (debug & 0x20) != 0;
 }

 public static void debug(int mode, String msg)
 {
     if((debug & mode) != 0)
         System.out.println(msg);
 }

 public static void debug(String msg)
 {
     if(debugMode)
         System.out.println(msg);
 }

 public static boolean editorDebug()
 {
     return (debug & 2) != 0;
 }

 public static void error(String msg)
 {
     System.err.println(msg);
 }

 public static boolean ldapDebug()
 {
     return (debug & 4) != 0;
 }

 public static void msg(String msg)
 {
     System.out.println(msg);
 }

 public static void setDebug(String dbgStr)
 {
     if(dbgStr == null)
         return;
     for(StringTokenizer tokens = new StringTokenizer(dbgStr, ":"); tokens.hasMoreTokens();)
     {
         String token = tokens.nextToken();
         if(token.equalsIgnoreCase("editor"))
             debug |= 2;
         else
         if(token.equalsIgnoreCase("ldap"))
             debug |= 4;
         else
         if(token.equalsIgnoreCase("level1"))
         {
             debug |= 8;
             debugMode = true;
         } else
         if(token.equalsIgnoreCase("level2"))
         {
             debug |= 8;
             debug |= 0x10;
             debugMode = true;
         } else
         if(token.equalsIgnoreCase("ssl"))
             debug |= 0x20;
         else
         if(token.equalsIgnoreCase("all"))
         {
             debug |= 0x7fffffff;
             debugMode = true;
         }
     }

 }

 public static final String DEBUG_PROPERTY = "lbe.debug";
 public static final int EDITOR_DEBUG = 2;
 public static final int LDAP_DEBUG = 4;
 public static final int LEVEL1_DEBUG = 8;
 public static final int LEVEL2_DEBUG = 16;
 public static final int ASF_DEBUG = 32;
 public static int debug = 0;
 public static boolean debugMode = false;
 public static final boolean jndiDebug = false;
 public static final boolean stackTrace = false;
 public static final boolean sDebugMode = false;

 static 
 {
     SecurityManager sm = System.getSecurityManager();
     if(sm != null)
         try
         {
             sm.checkPropertyAccess("lbe.debug");
             setDebug(System.getProperty("lbe.debug"));
         }
         catch(Exception _ex)
         {
             System.err.println("Not allowed to obtain debug property");
         }
     else
         setDebug(System.getProperty("lbe.debug"));
 }
}