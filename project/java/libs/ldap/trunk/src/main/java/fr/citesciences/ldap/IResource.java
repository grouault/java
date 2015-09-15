//Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 11/01/2005 14:18:26
//Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
//Decompiler options: packimports(3) 
//Source File Name:   IResource.java

package fr.citesciences.ldap;

import java.awt.Color;
import java.util.HashMap;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class IResource
{

 public IResource(String name)
     throws MissingResourceException
 {
     bundle = ResourceBundle.getBundle("lbe.util.resources." + name, Locale.getDefault());
 }

 public IResource(ResourceBundle bundle)
 {
     this.bundle = bundle;
 }

 public String get(String nm)
 {
     return get(nm, null);
 }

 public String get(String nm, String defValue)
 {
     try
     {
         return bundle.getString(nm);
     }
     catch(MissingResourceException _ex)
     {
         return defValue;
     }
 }

 public ResourceBundle getBundle()
 {
     return bundle;
 }

 public Color getColor(String key, Color defColor)
 {
     try
     {
         String code = bundle.getString(key);
         return Color.decode(code);
     }
     catch(MissingResourceException _ex)
     {
         return defColor;
     }
     catch(NumberFormatException _ex)
     {
         return defColor;
     }
 }

 public static IResource getDefault()
 {
     return defaultResource;
 }

 public int getInt(String nm, int defValue)
 {
     try
     {
         String str = bundle.getString(nm);
         return Integer.parseInt(str);
     }
     catch(MissingResourceException _ex)
     {
         return defValue;
     }
     catch(NumberFormatException _ex)
     {
         return defValue;
     }
 }

 public static IResource getResource(String name)
 {
     if(bundles == null)
         bundles = new HashMap();
     IResource rs = (IResource)bundles.get(name);
     if(rs != null)
         return rs;
     try
     {
         rs = new IResource(name);
     }
     catch(MissingResourceException e)
     {
         System.err.println("IResource: Failed to load rb: " + name + " (" + e.getMessage() + ")");
         return defaultResource;
     }
     bundles.put(name, rs);
     return rs;
 }

 private static IResource defaultResource;
 private static HashMap bundles;
 private ResourceBundle bundle;

 static 
 {
     try
     {
         defaultResource = new IResource("browser");
     }
     catch(MissingResourceException e)
     {
         System.err.println("Error: " + e.getMessage());
         System.exit(-2);
     }
 }
}