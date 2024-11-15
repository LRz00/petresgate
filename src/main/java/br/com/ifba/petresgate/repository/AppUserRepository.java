/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.petresgate.repository;

import br.com.ifba.petresgate.domain.Animal;
import br.com.ifba.petresgate.domain.AppUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lara
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{

    public Optional<AppUser> findByEmail(String email);
    
}
