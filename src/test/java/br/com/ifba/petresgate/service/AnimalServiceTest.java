package br.com.ifba.petresgate.service;

import br.com.ifba.petresgate.domain.AppUser;
import br.com.ifba.petresgate.domain.DTOs.RegisterFormDTO;
import br.com.ifba.petresgate.repository.AppUserRepository;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.ifba.petresgate.domain.Animal;
import br.com.ifba.petresgate.repository.AnimalRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

    @Mock
    private MailService mailService;

    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------

    private RegisterFormDTO createFormInfo() {
        return RegisterFormDTO.builder()
                .email("test@example.com")
                .fullname("John Doe")
                .phoneNumber("123456789")
                .neighborhood("Downtown")
                .referencePoint("Near Park")
                .street("123 Main St")
                .breed("Labrador")
                .color("Black")
                .description("Friendly dog")
                .species("Dog")
                .build();
    }

    @Test
    void shouldUseExistingUserWhenEmailAlreadyExists() {
        // Arrange
        RegisterFormDTO formInfo = createFormInfo();
        AppUser existingUser = AppUser.builder()
                .fullName(formInfo.getFullname())
                .email(formInfo.getEmail())
                .phoneNumber(formInfo.getPhoneNumber())
                .build();

        Mockito.when(appUserRepository.findByEmail(formInfo.getEmail()))
                .thenReturn(Optional.of(existingUser));

        // Act
        animalService.saveAnimal(formInfo);

        // Assert
        Mockito.verify(appUserRepository, Mockito.never()).save(Mockito.any());
        Mockito.verify(animalRepository).save(Mockito.any(Animal.class));
        Mockito.verify(mailService).sendAnimalRegisteredEmail(existingUser, Mockito.any(Animal.class));
    }


    // -----------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------

    @Test
    void testSaveAnimal_NewUser() {
        // Arrange
        RegisterFormDTO formInfo = new RegisterFormDTO("John Doe", "john@example.com", "123456789", "dog", "brown", "friendly", "Labrador", "Main Street", "Downtown", "Near the big tree");
        when(appUserRepository.findByEmail(formInfo.getEmail())).thenReturn(Optional.empty());

        // Act
        animalService.saveAnimal(formInfo);

        // Assert
        verify(appUserRepository, times(1)).save(any(AppUser.class));
        verify(animalRepository, times(1)).save(any(Animal.class));
        verify(mailService, times(1)).sendAnimalRegisteredEmail(any(AppUser.class), any(Animal.class));
    }

    @Test
    void testSaveAnimal_ExistingUser() {
        // Arrange
        RegisterFormDTO formInfo = new RegisterFormDTO("John Doe", "john@example.com", "123456789", "dog", "brown", "friendly", "Labrador", "Main Street", "Downtown", "Near the big tree");
        AppUser existingUser = new AppUser("John Doe", "john@example.com", "123456789");
        when(appUserRepository.findByEmail(formInfo.getEmail())).thenReturn(Optional.of(existingUser));

        // Act
        animalService.saveAnimal(formInfo);

        // Assert
        verify(appUserRepository, times(0)).save(any(AppUser.class));
        verify(animalRepository, times(1)).save(any(Animal.class));
        verify(mailService, times(1)).sendAnimalRegisteredEmail(any(AppUser.class), any(Animal.class));
    }

    @Test
    void testSaveAnimal_NullInput() {
        // Act and Assert
        assertThrows(NullPointerException.class, () -> animalService.saveAnimal(null));
    }

    @Test
    void testSaveAnimal_EmptyInput() {
        // Arrange
        RegisterFormDTO formInfo = new RegisterFormDTO("", "", "", "", "", "", "", "", "", "");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> animalService.saveAnimal(formInfo));
    }


}