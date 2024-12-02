package br.com.ifba.petresgate.service.factory;

import br.com.ifba.petresgate.domain.Address;
import br.com.ifba.petresgate.domain.Animal;
import br.com.ifba.petresgate.domain.AppUser;
import br.com.ifba.petresgate.domain.DTOs.AnimalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class AnimalFactory {

    public Animal createAnimal(AnimalDTO animalInfo, AppUser user, Address address) {
        Animal animal = Animal.builder()
                .breed(animalInfo.getBreed())
                .color(animalInfo.getColor())
                .currentAddress(address)
                .description(animalInfo.getDescription())
                .registeredBy(user)
                .species(animalInfo.getSpecies())
                .addressHistory(new ArrayList<>())
                .build();

        address.setAnimal(animal);
        animal.getAddressHistory().add(address);

        return animal;
    }
}

