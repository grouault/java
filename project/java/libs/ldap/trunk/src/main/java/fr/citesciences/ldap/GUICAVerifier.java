//Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 11/01/2005 14:18:28
//Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
//Decompiler options: packimports(3) 
//Source File Name:   GUICAVerifier.java

package fr.citesciences.ldap;

import java.security.cert.X509Certificate;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


//Referenced classes of package lbe.ssl:
//         CAVerifier

public class GUICAVerifier
 implements CAVerifier
{

 public GUICAVerifier()
 {
 }

 public void chainVerifyFailed(int errorType)
 {
     String msg = null;
     switch(errorType)
     {
     case 1: // '\001'
         msg = "CA certificate is not in the server certificate chain.\nPlease use the keytool command to import the server certificate.";
         break;

     case 2: // '\002'
         msg = "Server certificate chain verification failed.";
         break;

     case 3: // '\003'
         msg = "Server certificate chain verification failed and \nthe CA is missing.";
         break;

     default:
         msg = "Unknown error.";
         break;
     }
     JOptionPane.showMessageDialog(null, msg, "SSL Error", 0);
 }

 private JPanel createCertInfoPanel(X509Certificate cert)
 {
//     JJPanel panel = new JJPanel();
//     panel.setAnchor(13);
//     panel.add(new JLabel("Subject: "), 0, 0, 1, 1);
//     panel.add(new JLabel("Valid from: "), 0, 1, 1, 1);
//     panel.add(new JLabel("Valid to: "), 0, 2, 1, 1);
//     panel.setAnchor(17);
//     JLabel lb = null;
//     lb = new JLabel(cert.getSubjectDN().getName());
//     lb.setForeground(Color.black);
//     panel.add(lb, 1, 0, 1, 1);
//     lb = new JLabel(cert.getNotBefore().toString());
//     lb.setForeground(Color.black);
//     panel.add(lb, 1, 1, 1, 1);
//     lb = new JLabel(cert.getNotAfter().toString());
//     lb.setForeground(Color.black);
//     panel.add(lb, 1, 2, 1, 1);
     return null;
 }

 public int isTrusted(X509Certificate caCert)
 {
     Object msg[] = new Object[2];
     msg[0] = "Do you want to trust the following CA certificate:";
     msg[1] = createCertInfoPanel(caCert);
     Object options[] = {
         "This session only", "Always", "No"
     };
     int v = JOptionPane.showOptionDialog(null, ((Object) (msg)), "Server CA Certificate missing", -1, 2, null, options, options[0]);
     if(v == 1)
         return 2;
     return v != 0 ? 1 : 3;
 }
}