package br.com.ifba.petresgate.domain.mapper;


import br.com.ifba.petresgate.domain.Address;
import br.com.ifba.petresgate.domain.DTOs.AddressDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressMapper {

    public static AddressDTO toAddressDTO(Address address) {
        return AddressDTO.builder()
                .street(address.getStreet())
                .neighborhood(address.getNeighborhood())
                .referencePoint(address.getReferencePoint())
                .build();
    }
}
