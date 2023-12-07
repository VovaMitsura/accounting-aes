package com.example.dal.entities;

import com.example.dal.security.identity.GrantedAuthority;
import com.example.dal.security.identity.SimpleGrantedAuthority;
import com.example.dal.security.identity.UserDetails;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.*;

@Entity
@Table(name = "users")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "first_name", length = 50, nullable = false)
  private String firstName;

  @Column(name = "last_name", length = 50, nullable = false)
  private String lastName;

  @Column(name = "middle_name", length = 50, nullable = false)
  private String middleName;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Column(name = "email", length = 50, nullable = false, unique = true)
  private String email;

  @Column(name = "password", length = 50, nullable = false)
  private String password;

  @Column(name = "date_of_emploment")
  private Date dateOfEmployment;

  @Column(name = "enabled")
  private Boolean enabled;

  @ManyToOne
  @JoinColumn(name = "department_id")
  private Department department;

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    User user = (User) o;
    return getId() != null && Objects.equals(getId(), user.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }

  @Override
  public Collection<? extends GrantedAuthority> getUserAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.toString()));
  }

  @Override
  public Boolean isEnabled() {
    return enabled;
  }
}
