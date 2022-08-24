package com.example.user_service.DTO.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserInDTO {

    private String name;
    private String surname;
    private String lastname;
    private String email;
}
