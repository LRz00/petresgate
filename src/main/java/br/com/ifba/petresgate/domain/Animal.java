/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.petresgate.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
/**
 *
 * @author lara
 * This class refers to the street animals that will be registered to the system 
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name="animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    
    @Column(name="species")
    private String species;
    
    @Column(name="color")
    private String color;
    
    @Column(name="description")
    private String description;
    
    @Column(name="breed")
    private String breed;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="current_address_id", referencedColumnName="id")
    private Address currentAddress;
    
    @OneToMany(mappedBy= "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addressHistory = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name="app_user_id")
    private AppUser registeredBy;
    
    @OneToMany(mappedBy="animal", cascade = CascadeType.ALL)
    private List<Comment> comments;
    
    public void addAddresToHistory(Address address){
        address.setAnimal(this);
        this.addressHistory.add(address);
    }
}
