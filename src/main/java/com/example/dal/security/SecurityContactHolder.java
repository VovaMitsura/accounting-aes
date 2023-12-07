package com.example.dal.security;

public class SecurityContactHolder {

  private static final SecurityContext securityContext;

  static {
    securityContext = new SecurityContextImpl();
  }

  public static SecurityContext getContext() {
    return securityContext;
  }

}
