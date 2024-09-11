package com.typeface.dropbox.jwt.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
