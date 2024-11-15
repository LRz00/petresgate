/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.petresgate.repository;

import br.com.ifba.petresgate.domain.Address;
import br.com.ifba.petresgate.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lara
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
    
}
