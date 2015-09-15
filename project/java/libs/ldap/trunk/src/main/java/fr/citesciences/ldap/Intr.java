//Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 11/01/2005 14:18:26
//Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
//Decompiler options: packimports(3) 
//Source File Name:   Intr.java

package fr.citesciences.ldap;

import java.awt.Color;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

//Referenced classes of package lbe.common:
//         Debug, IResource

public class Intr
{

 public Intr()
 {
 }

 public static String get(String nm)
 {
     try
     {
         return resources.getString(nm);
     }
     catch(MissingResourceException _ex)
     {
         return null;
     }
 }

 public static String get(String nm, String defValue)
 {
     try
     {
         return resources.getString(nm);
     }
     catch(MissingResourceException _ex)
     {
         return defValue;
     }
 }

 public static Color getColor(String key, Color defColor)
 {
     try
     {
         String code = resources.getString(key);
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

 public static int getInt(String nm, int defValue)
 {
     try
     {
         String str = resources.getString(nm);
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

 public static final String OK_BT_LB = "ok.button.lb";
 public static final String CANCEL_BT_LB = "cancel.button.lb";
 public static final String YES_BT_LB = "yes.button.lb";
 public static final String NO_BT_LB = "no.button.lb";
 public static final String SAVE_BT_LB = "save.button.lb";
 public static final String APPLY_BT_LB = "apply.button.lb";
 public static final String CREATE_BT_LB = "create.button.lb";
 public static final String RENAME_BT_LB = "rename.button.lb";
 public static final String MOVE_BT_LB = "move.button.lb";
 public static final String DELETE_BT_LB = "delete.button.lb";
 public static final String COPY_BT_LB = "copy.button.lb";
 public static final String EDIT_BT_LB = "edit.button.lb";
 public static final String NEW_BT_LB = "new.button.lb";
 public static final String CONNECT_BT_LB = "connect.button.lb";
 public static final String ANONCONNECT_BT_LB = "anonconnect.button.lb";
 private static ResourceBundle resources = IResource.getDefault().getBundle();

}