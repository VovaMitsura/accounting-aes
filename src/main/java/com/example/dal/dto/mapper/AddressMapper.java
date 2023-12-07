package com.example.dal.dto.mapper;

import com.example.dal.dto.AddressDto;
import com.example.dal.entities.Address;

public class AddressMapper {

  public static AddressDto toDto(Address address) {
    return new AddressDto(address.getId(),
            address.getStreet(),
            address.getCity(),
            address.getBuildingNumber(),
            address.getApartmentNumber(),
            address.getZipCode());
  }

  public static Address toEntity(AddressDto addressDto) {
    return new Address(addressDto.id(),
            addressDto.street(),
            addressDto.city(),
            addressDto.buildingNumber(),
            addressDto.apartmentNumber(),
            addressDto.zipCode(),
            null);
  }
}
