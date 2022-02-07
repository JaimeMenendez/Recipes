package com.jaime.recipes.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Not Authorized")
public class UnauthorizedException extends RuntimeException{
}
