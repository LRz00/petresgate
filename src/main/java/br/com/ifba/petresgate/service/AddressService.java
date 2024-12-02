package br.com.ifba.petresgate.service;


import br.com.ifba.petresgate.domain.Address;
import br.com.ifba.petresgate.domain.Animal;
import br.com.ifba.petresgate.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    public void addAddressToHistory(Animal animal, Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null.");
        }
        if (!animal.getAddressHistory().contains(address)) {
            animal.getAddressHistory().add(address);
        }
    }
}
