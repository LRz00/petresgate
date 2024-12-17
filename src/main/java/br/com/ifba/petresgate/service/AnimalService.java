/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.petresgate.service;

import br.com.ifba.petresgate.domain.Address;
import br.com.ifba.petresgate.domain.Animal;
import br.com.ifba.petresgate.service.factory.AddressFactory;
import br.com.ifba.petresgate.service.factory.AnimalFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import br.com.ifba.petresgate.repository.*;
import br.com.ifba.petresgate.domain.DTOs.RegisterFormDTO;
import br.com.ifba.petresgate.domain.AppUser;
import br.com.ifba.petresgate.domain.DTOs.UpdateFormDTO;
import br.com.ifba.petresgate.exception.AnimalInformationConflictException;
import br.com.ifba.petresgate.exception.ObjectNotFoundException;
import br.com.ifba.petresgate.exception.InvalidUserCredentialsException;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lara
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AppUserRepository userRepository;
    private final MailService mailService;
    private final AppUserService userService;
    private final AddressService addressService;
    private final AddressFactory addressFactory;
    private final AnimalFactory animalFactory;

    @Transactional
    public void saveAnimal(RegisterFormDTO formInfo) {
        validateAnimalUniqueness(
                formInfo.getAnimalInfo().getSpecies(),
                formInfo.getAnimalInfo().getColor(),
                formInfo.getAnimalInfo().getDescription()
        );

        AppUser user = userService.getOrCreateUser(formInfo.getUserInfo());
        Address currentAddress = addressFactory.createAddress(formInfo.getAddressInfo());
        Animal animal = animalFactory.createAnimal(formInfo.getAnimalInfo(), user, currentAddress);
        saveAnimalAndAddress(animal, currentAddress);
        mailService.sendAnimalRegisteredEmail(user, animal);
    }

    private void saveAnimalAndAddress(Animal animal, Address currentAddress) {
        animal.setCurrentAddress(currentAddress);
        currentAddress.setAnimal(animal);
        animalRepository.save(animal);
    }


    public Boolean canAnimalBeEdited(Long animalId, UUID userKey) {
        AppUser user = this.userRepository.findByConfirmationKey(userKey).orElseThrow(()
                -> new InvalidUserCredentialsException("Confirmation Key doesnt match a valid user"));

        Animal animal = this.animalRepository.findById(animalId).orElseThrow(() -> new ObjectNotFoundException("Animal not found"));

        return animal.getRegisteredBy().getConfirmationKey().equals(user.getConfirmationKey());
    }

    private void saveAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    @Transactional
    public void updateAnimal(Long animalId, UUID userKey, UpdateFormDTO updateForm) {
        Animal animal = getAnimalById(animalId);
        if (canAnimalBeEdited(animalId, userKey)) {

            Address currentAddress = animal.getCurrentAddress();
            if (currentAddress != null) {
                addressService.addAddressToHistory(animal, currentAddress);
            }
            Address newAddress = addressFactory.createAddress(updateForm.getAddressInfo());
            animal.setCurrentAddress(newAddress);
            saveAnimal(animal);
            sendAnimalEditedEmail(userKey, animal);
        }
    }

    public Animal getAnimalById(Long animalId) {
        return animalRepository.findByIdWithAddressHistory(animalId)
                .orElseThrow(() -> new ObjectNotFoundException("Animal not found"));
    }

    private void sendAnimalEditedEmail(UUID userKey, Animal animal) {
        AppUser appUser = this.userRepository.findByConfirmationKey(userKey)
                .orElseThrow(() -> new InvalidUserCredentialsException("User not found"));
        this.mailService.sendAnimalEditedEmail(appUser, animal);
    }
    
    public Page<Animal> getAllAnimals(Pageable pageable){
        return this.animalRepository.findAll(pageable);
    }

    public void validateAnimalUniqueness(String species, String color, String description) {
        animalRepository.findBySpeciesAndColorAndDescription(species, color, description)
                .ifPresent(existingAnimal -> {
                    throw new AnimalInformationConflictException("Animal already registered with the same characteristics.");
                });
    }
}
