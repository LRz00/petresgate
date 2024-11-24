/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.petresgate.service;

import br.com.ifba.petresgate.domain.Address;
import br.com.ifba.petresgate.domain.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import br.com.ifba.petresgate.repository.*;
import br.com.ifba.petresgate.domain.DTOs.RegisterFormDTO;
import br.com.ifba.petresgate.domain.AppUser;
import br.com.ifba.petresgate.domain.DTOs.UpdateFormDTO;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author lara
 */
@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AppUserRepository userRepository;
    private final MailService mailService;

    public void saveAnimal(RegisterFormDTO formInfo) {
        AppUser user = userRepository.findByEmail(formInfo.getEmail())
                .orElseGet(() -> {
                    AppUser newUser = AppUser.builder()
                            .fullname(formInfo.getFullname())
                            .email(formInfo.getEmail())
                            .phoneNumber(formInfo.getPhoneNumber())
                            .build();
                    return userRepository.save(newUser);
                });

        Address address = Address.builder().neighborhood(formInfo.getNeighborhood())
                .referencePoint(formInfo.getReferencePoint()).street(formInfo.getStreet())
                .build();

        Animal animal = Animal.builder().breed(formInfo.getBreed()).color(formInfo.getColor())
                .currentAddress(address).description(formInfo.getDescription())
                .registeredBy(user).species(formInfo.getSpecies()).build();

        //animal.addAddresToHistory(address);
        this.animalRepository.save(animal);

        this.mailService.sendAnimalRegisteredEmail(user, animal);
    }

    public Boolean canAnimalBeEdited(Long animalId, UUID userKey) {
        AppUser user = this.userRepository.findByConfirmationKey(userKey).orElseThrow(()
                -> new RuntimeException("Confirmation Key doesnt match a valid user"));

        Animal animal = this.animalRepository.findById(animalId).orElseThrow(() -> new RuntimeException("Animal not found"));

        if (animal.getRegisteredBy().getConfirmationKey().equals(user.getConfirmationKey())) {
            return true;
        } else {
            return false;
        }
    }

    public void UpdateAnimal(Long animalId, UUID userKey, UpdateFormDTO updateForm) {
        
        Animal animal = this.animalRepository.findById(animalId).orElseThrow(() -> new RuntimeException("Animal not found"));

        if (this.canAnimalBeEdited(animalId, userKey)) {
            Address address = Address.builder()
                    .neighborhood(updateForm.getNeighborhood() != null ? updateForm.getNeighborhood() : animal.getCurrentAddress().getNeighborhood())
                    .referencePoint(updateForm.getReferencePoint() != null ? updateForm.getReferencePoint() : animal.getCurrentAddress().getReferencePoint())
                    .street(updateForm.getStreet() != null ? updateForm.getStreet() : animal.getCurrentAddress().getStreet())
                    .build();

           

            animal.setCurrentAddress(address);
            //animal.addAddresToHistory(address);

            this.animalRepository.save(animal);

            this.mailService.sendAnimalEditedEmail(this.userRepository.findByConfirmationKey(userKey).get(), animal);
        }
    }
    
    public Page<Animal> getAllAnimals(Pageable pageable){
        return this.animalRepository.findAll(pageable);
    }
}
