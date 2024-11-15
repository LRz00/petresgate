/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.petresgate.web;
import br.com.ifba.petresgate.domain.DTOs.RegisterFormDTO;
import br.com.ifba.petresgate.service.AnimalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author lara
 */
@RestController
@RequestMapping("/api/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<String> saveAnimal(@Valid @RequestBody RegisterFormDTO formInfo) {
        animalService.saveAnimal(formInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body("Animal registered successfully!");
    }
}