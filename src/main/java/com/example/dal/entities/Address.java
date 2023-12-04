package com.example.dal.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "addresses")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "city", nullable = false, length = 50)
  private String city;

  @Column(name = "street", nullable = false, length = 50)
  private String street;

  @Column(name = "building_number", nullable = false, length = 50)
  private String buildingNumber;

  @Column(name = "apartment_number", nullable = false, length = 50)
  private String apartmentNumber;

  @Column(name = "zip_code", nullable = false, length = 50)
  private String zipCode;

  @OneToOne(mappedBy = "address")
  private NuclearPowerPlant nuclearPowerPlant;

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    Address address = (Address) o;
    return getId() != null && Objects.equals(getId(), address.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
