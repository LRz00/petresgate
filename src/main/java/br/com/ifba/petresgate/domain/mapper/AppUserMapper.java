package br.com.ifba.petresgate.domain.mapper;

import br.com.ifba.petresgate.domain.AppUser;
import br.com.ifba.petresgate.domain.DTOs.AppUserDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppUserMapper {

    public static AppUserDTO toAppUserDTO(AppUser appUser) {
        return AppUserDTO.builder()
                .fullName(appUser.getFullName())
                .email(appUser.getEmail())
                .phoneNumber(appUser.getPhoneNumber())
                .confirmationKey(appUser.getConfirmationKey())
                .build();
    }
}
