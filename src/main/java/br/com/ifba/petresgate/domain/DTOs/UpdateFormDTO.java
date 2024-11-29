/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.petresgate.domain.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 *
 * @author lara
 *
 * You're only allowed to edit address information
 * the actual details of the animal shouldn't change
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateFormDTO {

    @NotNull(message = "Address details are required")
    private AddressDTO addressInfo;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class AddressDTO {
        @NotBlank(message = "Street is required")
        private String street;

        @NotBlank(message = "Neighborhood is required")
        private String neighborhood;

        @NotBlank(message = "Reference Point is required")
        private String referencePoint;
    }
}
