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
import java.util.UUID;


/**
 *
 * @author lara
 * 
 * Youre only allowed to edit address information
 * the actual details of the animal shouldn't change
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateFormDTO {
    
    private String street;
    private String neighborhood;
    private String referencePoint;
}
