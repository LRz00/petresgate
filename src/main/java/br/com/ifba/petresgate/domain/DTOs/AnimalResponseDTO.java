package br.com.ifba.petresgate.domain.DTOs;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimalResponseDTO {
    private Long id;
    private String species;
    private String color;
    private String description;
    private String breed;
    private AddressDTO currentAddress;
    private List<AddressDTO> addressHistory;
    private AppUserDTO registeredBy;
    private List<CommentDTO> comments;
}
