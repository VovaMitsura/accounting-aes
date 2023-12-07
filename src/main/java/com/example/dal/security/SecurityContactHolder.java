package com.example.dal.security;

public class SecurityContactHolder {

  private static SecurityContext securityContext;

  static {
    securityContext = new SecurityContextImpl();
  }

  public static SecurityContext getContext() {
    return securityContext;
  }

  public static void setContext(SecurityContext context) {
    securityContext = context;
  }

}
