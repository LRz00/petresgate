package br.com.ifba.petresgate.domain.DTOs;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {

    private String street;
    private String neighborhood;
    private String referencePoint;
    private Long animalId;
}
