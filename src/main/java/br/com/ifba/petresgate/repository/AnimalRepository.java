/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.petresgate.repository;

import br.com.ifba.petresgate.domain.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author lara
 */
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>{

    @Query("SELECT a FROM Animal a LEFT JOIN a.addressHistory WHERE a.id = :id")
    Optional<Animal> findByIdWithAddressHistory(@Param("id") Long id);

    Optional<Animal> findBySpeciesAndColorAndDescription(String species, String color, String description);
}
