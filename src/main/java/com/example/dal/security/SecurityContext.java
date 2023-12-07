package com.example.dal.security;

public interface SecurityContext {

  Authentication getAuthentication();
  void setAuthentication(Authentication authentication);
}
