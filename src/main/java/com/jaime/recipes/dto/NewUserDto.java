package com.jaime.recipes.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class NewUserDto {
    @Pattern(regexp = ".+@.+\\..+", message = "Email should contain @ and . symbols")
    @NotNull(message = "Email should not be null")
    private String email;

    @NotNull(message = "Password must not be null.")
    @NotBlank(message = "Password must not be blank.")
    @Size(min = 8, message = "Password must have at least 8 characters.")
    private String password;
}
