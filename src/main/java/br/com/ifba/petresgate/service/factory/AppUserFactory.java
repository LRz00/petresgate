package br.com.ifba.petresgate.service.factory;

import br.com.ifba.petresgate.domain.AppUser;
import br.com.ifba.petresgate.domain.DTOs.AppUserDTO;
import org.springframework.stereotype.Component;

@Component
public class AppUserFactory {

    public AppUser createAppUser(AppUserDTO appUserInfo) {
        return AppUser.builder()
                .fullName(appUserInfo.getFullName())
                .email(appUserInfo.getEmail())
                .phoneNumber(appUserInfo.getPhoneNumber())
                .build();
    }
}
