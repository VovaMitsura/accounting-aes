package com.example.dal.security;

import com.example.dal.security.identity.UserDetails;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class UserEmailPasswordAuthenticationImpl implements Authentication {

  private UserDetails userDetails;
}
