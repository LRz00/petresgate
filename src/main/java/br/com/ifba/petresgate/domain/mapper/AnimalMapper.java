package br.com.ifba.petresgate.domain.mapper;

import br.com.ifba.petresgate.domain.Animal;
import br.com.ifba.petresgate.domain.DTOs.AnimalInfoDTO;
import br.com.ifba.petresgate.domain.DTOs.AnimalResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnimalMapper {

    public static AnimalResponseDTO toAnimalResponseDTO(Animal animal) {
        return AnimalResponseDTO.builder()
                .id(animal.getId())
                .species(animal.getSpecies())
                .color(animal.getColor())
                .description(animal.getDescription())
                .breed(animal.getBreed())
                .currentAddress(AddressMapper.toAddressDTO(animal.getCurrentAddress()))
                .addressHistory(animal.getAddressHistory().stream().map(AddressMapper::toAddressDTO).toList())
                .registeredBy(AppUserMapper.toAppUserDTO(animal.getRegisteredBy()))
                .comments(animal.getComments().stream().map(CommentMapper::toCommentDTO).toList())
                .build();
    }

    public static AnimalInfoDTO toAnimalInfoDTO(Animal animal) {
        return AnimalInfoDTO.builder()
                .id(animal.getId())
                .species(animal.getSpecies())
                .color(animal.getColor())
                .description(animal.getDescription())
                .breed(animal.getBreed())
                .currentAddress(AddressMapper.toAddressDTO(animal.getCurrentAddress()))
                .addressHistory(animal.getAddressHistory().stream().map(AddressMapper::toAddressDTO).toList())
                .registeredBy(AppUserMapper.toAppUserDTO(animal.getRegisteredBy()))
                .comments(animal.getComments().stream().map(CommentMapper::toCommentDTO).toList())
                .build();
    }
}
