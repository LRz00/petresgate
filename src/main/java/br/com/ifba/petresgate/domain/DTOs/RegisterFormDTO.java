/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.petresgate.domain.DTOs;

import jakarta.validation.constraints.Email;
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
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterFormDTO {
    //user info
    @NotNull
    @NotBlank
    private String fullname;
    @NotNull
    @NotBlank
    @Email
    private String email;    
    @NotNull
    @NotBlank
    private String phoneNumber;
    
    //animal info
    @NotNull
    @NotBlank
    private String species; //cat or dog
    @NotNull
    @NotBlank
    private String color;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private String breed;
    
    //animal address info
    @NotNull
    @NotBlank
    private String street;
    @NotNull
    @NotBlank
    private String neighborhood;
    @NotNull
    @NotBlank
    private String referencePoint;
}
