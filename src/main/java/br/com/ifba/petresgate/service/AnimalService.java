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

import java.util.ArrayList;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static org.hibernate.query.sqm.tree.SqmNode.log;

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

    @Transactional
    public void saveAnimal(RegisterFormDTO formInfo) {
        AppUser user = userRepository.findByEmail(formInfo.getUserInfo().getEmail())
                .orElseGet(() -> userRepository.save(AppUser.builder()
                        .fullName(formInfo.getUserInfo().getFullName())
                        .email(formInfo.getUserInfo().getEmail())
                        .phoneNumber(formInfo.getUserInfo().getPhoneNumber())
                        .build()));

        animalRepository.findBySpeciesAndColorAndDescription(
                formInfo.getAnimalInfo().getSpecies(),
                formInfo.getAnimalInfo().getColor(),
                formInfo.getAnimalInfo().getDescription()
        ).ifPresent(existingAnimal -> {
            throw new RuntimeException("Animal já registrado com as mesmas características.");
        });

        Address currentAddress = Address.builder()
                .neighborhood(formInfo.getAddressInfo().getNeighborhood())
                .referencePoint(formInfo.getAddressInfo().getReferencePoint())
                .street(formInfo.getAddressInfo().getStreet())
                .build();

        Animal animal = Animal.builder()
                .breed(formInfo.getAnimalInfo().getBreed())
                .color(formInfo.getAnimalInfo().getColor())
                .currentAddress(currentAddress)
                .description(formInfo.getAnimalInfo().getDescription())
                .registeredBy(user)
                .species(formInfo.getAnimalInfo().getSpecies())
                .addressHistory(new ArrayList<>()) // Inicializa a lista diretamente
                .build();

        currentAddress.setAnimal(animal);
        animal.getAddressHistory().add(currentAddress);

        animalRepository.save(animal);

        try {
            mailService.sendAnimalRegisteredEmail(user, animal);
        } catch (Exception e) {
            log.error("Erro ao enviar e-mail de registro para o animal: {}", animal.getId(), e);
        }
    }


//    public void saveAnimal(RegisterFormDTO formInfo) {
//        // Extrair os dados do formulário
//        RegisterFormDTO.UserInfoDTO userInfo = formInfo.getUserInfo();
//        RegisterFormDTO.AnimalInfoDTO animalInfo = formInfo.getAnimalInfo();
//        RegisterFormDTO.AddressDTO addressInfo = formInfo.getAddressInfo();
//
//        // Verificar se o usuário já existe ou criar um novo
//        AppUser user = userRepository.findByEmail(userInfo.getEmail())
//                .orElseGet(() -> {
//                    AppUser newUser = AppUser.builder()
//                            .fullName(userInfo.getFullName())
//                            .email(userInfo.getEmail())
//                            .phoneNumber(userInfo.getPhoneNumber())
//                            .build();
//                    return userRepository.save(newUser);
//                });
//
//        // Criar o endereço com base nas informações fornecidas
//        Address address = Address.builder()
//                .neighborhood(addressInfo.getNeighborhood())
//                .referencePoint(addressInfo.getReferencePoint())
//                .street(addressInfo.getStreet())
//                .build();
//
//        // Criar o animal com base nas informações fornecidas
//        Animal animal = Animal.builder()
//                .breed(animalInfo.getBreed())
//                .color(animalInfo.getColor())
//                .currentAddress(address)
//                .description(animalInfo.getDescription())
//                .registeredBy(user)
//                .species(animalInfo.getSpecies())
//                .build();
//
//        // Salvar o animal no repositório
//        this.animalRepository.save(animal);
//
//        // Enviar e-mail de confirmação para o usuário
//        this.mailService.sendAnimalRegisteredEmail(user, animal);
//    }


    public Boolean canAnimalBeEdited(Long animalId, UUID userKey) {
        AppUser user = this.userRepository.findByConfirmationKey(userKey).orElseThrow(()
                -> new RuntimeException("Confirmation Key doesnt match a valid user"));

        Animal animal = this.animalRepository.findById(animalId).orElseThrow(() -> new RuntimeException("Animal not found"));

        return animal.getRegisteredBy().getConfirmationKey().equals(user.getConfirmationKey());
    }

    public void updateAnimal(Long animalId, UUID userKey, UpdateFormDTO updateForm) {

        UpdateFormDTO.AddressDTO addressInfo = updateForm.getAddressInfo();

        Animal animal = this.animalRepository.findById(animalId).orElseThrow(() -> new RuntimeException("Animal not found"));

        if (this.canAnimalBeEdited(animalId, userKey)) {
            Address currentAddress = animal.getCurrentAddress();
            this.addAddressToHistory(animal, currentAddress);

            Address address = Address.builder()
                    .neighborhood(addressInfo.getNeighborhood() != null ? addressInfo.getNeighborhood() : animal.getCurrentAddress().getNeighborhood())
                    .referencePoint(addressInfo.getReferencePoint() != null ? addressInfo.getReferencePoint() : animal.getCurrentAddress().getReferencePoint())
                    .street(addressInfo.getStreet() != null ? addressInfo.getStreet() : animal.getCurrentAddress().getStreet())
                    .build();

            animal.setCurrentAddress(address);

            currentAddress.setAnimal(animal);
            animal.getAddressHistory().add(currentAddress);

            this.animalRepository.save(animal);

            this.mailService.sendAnimalEditedEmail(this.userRepository.findByConfirmationKey(userKey).get(), animal);
        }
    }
    
    public Page<Animal> getAllAnimals(Pageable pageable){
        return this.animalRepository.findAll(pageable);
    }

    public void addAddressToHistory(Animal animal, Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null.");
        }

        if (!animal.getAddressHistory().contains(address)) {
            animal.getAddressHistory().add(address);
        }
    }

//    public Animal getAnimalById(Long id) {
//        return this.animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal not found"));
//    }

    public Animal getAnimalById(Long id) {
        return animalRepository.findByIdWithAddressHistory(id)
                .orElseThrow(() -> new RuntimeException("Animal not found"));
    }
}
