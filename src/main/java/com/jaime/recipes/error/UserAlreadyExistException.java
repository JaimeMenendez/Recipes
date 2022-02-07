package com.jaime.recipes.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Username already exist!")
public class UserAlreadyExistException extends RuntimeException{
}
