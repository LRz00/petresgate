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
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentDTO {
    @NotNull
    @NotBlank
    private String fullname;
    
    @NotNull
    @NotBlank
    private String content;
}
