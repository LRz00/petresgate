package br.com.ifba.petresgate.service;

import br.com.ifba.petresgate.domain.AppUser;
import br.com.ifba.petresgate.domain.DTOs.AppUserDTO;
import br.com.ifba.petresgate.domain.DTOs.RegisterFormDTO;
import br.com.ifba.petresgate.repository.AppUserRepository;
import br.com.ifba.petresgate.service.factory.AppUserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserFactory appUserFactory;
    private final AppUserRepository appUserRepository;

    public AppUser getOrCreateUser(AppUserDTO userInfo) {
        return appUserRepository.findByEmail(userInfo.getEmail())
                .orElseGet(() -> appUserRepository.save(appUserFactory.createAppUser(userInfo)));
    }
}

