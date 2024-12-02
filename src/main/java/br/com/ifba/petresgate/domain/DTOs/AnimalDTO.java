package br.com.ifba.petresgate.domain.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AnimalDTO {
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
