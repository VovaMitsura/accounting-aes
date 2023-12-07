package com.example.dal.security.identity;

public record SimpleGrantedAuthority(String authority) implements GrantedAuthority {
  @Override
  public String getAuthority() {
    return authority;
  }
}
