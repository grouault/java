//Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 11/01/2005 14:18:26
//Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
//Decompiler options: packimports(3) 
//Source File Name:   Common.java

package fr.citesciences.ldap;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.*;
import javax.swing.JComponent;

//Referenced classes of package lbe.common:
//         Config, Debug, IResource

public class Common
{

 public Common()
 {
 }

 public static void add(Container ct, JComponent c, GridBagLayout gbl, GridBagConstraints gbc, int x, int y, int w, int h)
 {
     gbc.gridx = x;
     gbc.gridy = y;
     gbc.gridwidth = w;
     gbc.gridheight = h;
     gbl.setConstraints(c, gbc);
     ct.add(c);
 }

 public static Hashtable args(String cmdln, String withValues[], String noValues[])
 {
     Hashtable args = new Hashtable();
     Hashtable prop = toHashtable(withValues, noValues);
     for(StringTokenizer tokens = new StringTokenizer(cmdln); tokens.hasMoreTokens();)
     {
         String token = tokens.nextToken().trim();
         String tmp = (String)prop.get(token);
         if(tmp == null)
             System.err.println("invalid parameter!" + token);
         else
         if(tmp.equals("O"))
         {
             if(tokens.hasMoreTokens())
                 args.put(token, tokens.nextToken().trim());
             else
                 System.err.println("value is not specifed for " + token);
         } else
         if(tmp.equals("S"))
             args.put(token, "");
     }

     return args;
 }

 public static void fixSize(Component c)
 {
     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     java.awt.Point wPos = c.getLocation();
     Dimension wSize = c.getSize();
     if(wSize.width > screenSize.width)
         wSize.width = screenSize.width / 2;
     if(wSize.height < 80)
         wSize.height = 80;
     if(wSize.height > screenSize.height)
         wSize.height = screenSize.height / 2;
     c.setSize(wSize);
 }

 public static String format(String pattern, Object argument)
 {
     return MessageFormat.format(pattern, new Object[] {
         argument
     });
 }

 public static String format(byte value[])
 {
     return format(value, true);
 }

 public static String format(byte value[], boolean showText)
 {
     String str;
     if(showText)
         str = "BINARY (";
     else
         str = " ";
     if(value.length < 1024)
         str = str + value.length + "b";
     else
         str = str + value.length / 1024 + "Kb";
     if(showText)
         str = str + ")";
     return str;
 }

 public static boolean isNullOrEmpty(String tmp)
 {
     return tmp == null || tmp != null && tmp.length() == 0;
 }

 public static String limit(String str, int length)
 {
     if(str.length() > length)
         return str.substring(0, length) + "...";
     else
         return str;
 }

 private static BufferedReader open(String base, String file)
     throws Exception
 {
     Debug.debug("opening: " + base + file);
     URL u = new URL(base + file);
     URLConnection con = u.openConnection();
     InputStreamReader input = new InputStreamReader(con.getInputStream());
     BufferedReader reader = new BufferedReader(input);
     return reader;
 }

 public static BufferedReader openFile(String file)
     throws Exception
 {
     String base = Config.getBaseLocation();
     return open(base, file);
 }

 public static BufferedReader openFile(String dir, String file)
     throws Exception
 {
     String base = Config.getBaseLocation();
     char sep = base.charAt(base.length() - 1);
     return open(base + dir + sep, file);
 }

 public static BufferedReader openUrl(String url)
     throws Exception
 {
     Debug.debug("opening: " + url);
     URL u = new URL(url);
     URLConnection con = u.openConnection();
     InputStreamReader input = new InputStreamReader(con.getInputStream());
     BufferedReader reader = new BufferedReader(input);
     return reader;
 }

 public static String[] parseArgs(String args)
 {
     Vector argsV = new Vector(3);
     StringBuffer token = null;
     int length = args.length();
     for(int i = 0; i < length; i++)
     {
         char c = args.charAt(i);
         if(c == ' ')
         {
             if(token != null)
             {
                 argsV.addElement(token.toString());
                 token = null;
             }
         } else
         if(c == '"')
         {
             if(token == null)
                 token = new StringBuffer();
             for(int j = i + 1; j < length;)
             {
                 c = args.charAt(j);
                 if(c == '"')
                 {
                     argsV.addElement(token.toString());
                     token = null;
                     i++;
                     break;
                 }
                 token.append(c);
                 j++;
                 i++;
             }

             if(token != null)
             {
                 System.err.println("Invalid token: " + token);
                 token = null;
             }
         } else
         {
             if(token == null)
                 token = new StringBuffer();
             token.append(c);
         }
     }

     if(token != null)
         argsV.addElement(token.toString());
     return toStringArray(argsV);
 }

 public static byte[] readAsBytes(File f)
 {
     byte p[] = new byte[(int)f.length()];
     try
     {
         FileInputStream fos = new FileInputStream(f);
         fos.read(p);
         fos.close();
     }
     catch(IOException ioe)
     {
         System.err.println(ioe);
         return null;
     }
     return p;
 }

 public static String replace(String oldS, String newS, String buffer)
 {
     int minSize = oldS.length();
     int size = buffer.length();
     if(minSize > size)
         return buffer;
     StringBuffer newBuffer = new StringBuffer();
     for(int i = 0; i < size; i++)
         if(buffer.regionMatches(true, i, oldS, 0, minSize))
         {
             newBuffer.append(newS);
             i += minSize - 1;
         } else
         {
             newBuffer.append(buffer.charAt(i));
         }

     return newBuffer.toString();
 }

 public static void saveAsBytes(File f, byte i[])
 {
     try
     {
         FileOutputStream fos = new FileOutputStream(f);
         fos.write(i, 0, i.length);
         fos.flush();
         fos.close();
     }
     catch(IOException ioe)
     {
         System.err.println(ioe);
     }
 }

 public static void sleep(int msec)
 {
     try
     {
         Thread.sleep(msec);
     }
     catch(Exception _ex) { }
 }

 private static Hashtable toHashtable(String options[], String switches[])
 {
     Hashtable h = new Hashtable();
     for(int i = 0; i < options.length; i++)
         h.put(options[i], "O");

     for(int i = 0; i < switches.length; i++)
         h.put(switches[i], "S");

     return h;
 }

 public static int toInt(String value, int defaultValue)
 {
     try
     {
         return Integer.parseInt(value);
     }
     catch(Exception _ex)
     {
         return defaultValue;
     }
 }

 public static String[] toStringArray(Vector v)
 {
     if(v == null)
     {
         return null;
     } else
     {
         String ar[] = new String[v.size()];
         v.toArray(ar);
         return ar;
     }
 }

 public static void updateColors()
 {
     IResource resource = IResource.getDefault();
     warningColor = resource.getColor("warning.color", Color.yellow);
     errorColor = resource.getColor("error.color", Color.red);
     msgColor = resource.getColor("message.color", Color.black);
 }

 public static PrintWriter write(String base, String file)
     throws Exception
 {
     File f = new File(base, file);
     Debug.debug("writting: " + f.getAbsolutePath());
     PrintWriter d = new PrintWriter(new FileOutputStream(f));
     return d;
 }

 public static PrintWriter writeFile(String file)
     throws Exception
 {
     String base = Config.getBaseLocation();
     URL u = new URL(base);
     return write(u.getFile(), file);
 }

 public static PrintWriter writeFile(String dir, String file)
     throws Exception
 {
     String base = Config.getBaseLocation();
     char sep = base.charAt(base.length() - 1);
     URL u = new URL(base);
     return write(u.getFile() + dir + sep, file);
 }

 public static Color warningColor = null;
 public static Color errorColor = null;
 public static Color msgColor = null;
 public static final Cursor waitCursor = new Cursor(3);
 public static final Cursor normCursor = new Cursor(0);
 public static String lineSeparator = System.getProperty("line.separator");
 public static int fixSize = 10;

 static 
 {
     updateColors();
 }
}