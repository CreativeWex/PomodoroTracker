package com.bereznev.webapp.exception;
/*
    =====================================
    @project TaskManager
    @created 07/03/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class SessionOpeningException extends RuntimeException {

    private final String methodName;

    public SessionOpeningException(String methodName) {
        super(String.format("Session opening error: %s", methodName));
        this.methodName = methodName;
    }
}
