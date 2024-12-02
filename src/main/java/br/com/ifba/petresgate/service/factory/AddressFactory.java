package br.com.ifba.petresgate.service.factory;

import br.com.ifba.petresgate.domain.Address;
import br.com.ifba.petresgate.domain.DTOs.AddressDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressFactory {

    public Address createAddress(AddressDTO addressInfo) {
        return Address.builder()
                .neighborhood(addressInfo.getNeighborhood())
                .referencePoint(addressInfo.getReferencePoint())
                .street(addressInfo.getStreet())
                .build();
    }

}

