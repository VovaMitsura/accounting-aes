package com.example.dal.dto;

import java.util.UUID;

public record AddressDto(UUID id,
                         String street,
                         String city,
                         String buildingNumber,
                         String apartmentNumber,
                         String zipCode) {
}
