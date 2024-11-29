/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.petresgate.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import org.hibernate.annotations.UuidGenerator;
/**
 *
 * @author lara
 * This class has the fields for the people who will register the animals, 
 * the word 'user' is a reserved word in postgres hence the name "AppUser"
 */

@Entity
@Table(name="app_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    
    @Column(name= "full_name", nullable= false)
    private String fullName;
    
    @Column(name="email")
    @Email(message="Please try a VALID E-mail address")
    private String email;
    
    @Column(name="phone_number")
    private String phoneNumber;
    
    @UuidGenerator
    @Column(unique = true, name="confirmation_key",nullable=false, updatable = false)
    private UUID confirmationKey;

}
