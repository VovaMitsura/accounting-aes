package com.example.dal.security;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class SecurityContextImpl implements SecurityContext {
  private Authentication authentication;
}
