package com.example.dal.security.identity;

import java.util.Collection;

public interface UserDetails {


  String getEmail();
  String getPassword();
  Collection<? extends GrantedAuthority> getUserAuthorities();
  Boolean isEnabled();

}
