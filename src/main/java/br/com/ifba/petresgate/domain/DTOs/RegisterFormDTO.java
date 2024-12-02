package br.com.ifba.petresgate.domain.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for registering a user, animal, and address
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterFormDTO {
    @NotNull
    private AppUserDTO userInfo;

    @NotNull
    private AnimalDTO animalInfo;

    @NotNull
    private AddressDTO addressInfo;
}
