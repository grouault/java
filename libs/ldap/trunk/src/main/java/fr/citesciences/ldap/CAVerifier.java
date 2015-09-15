//Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 11/01/2005 14:18:28
//Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
//Decompiler options: packimports(3) 
//Source File Name:   CAVerifier.java

package fr.citesciences.ldap;

import java.security.cert.X509Certificate;

public interface CAVerifier
{

 public abstract void chainVerifyFailed(int i);

 public abstract int isTrusted(X509Certificate x509certificate);

 public static final int CA_NOT_TRUSTED = 1;
 public static final int CA_ALWYAS_TRUSTED = 2;
 public static final int CA_SESSION_TRUSTED = 3;
 public static final int NO_CA_CERT_ERROR = 1;
 public static final int CHAIN_VERIFY_ERROR = 2;
 public static final int NO_CA_AND_CHAIN_VERIFY_ERROR = 3;
}