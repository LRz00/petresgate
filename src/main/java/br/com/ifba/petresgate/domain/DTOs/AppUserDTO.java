package br.com.ifba.petresgate.domain.DTOs;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserDTO {

    private String fullName;
    private String email;
    private String phoneNumber;
    private UUID confirmationKey;
}
