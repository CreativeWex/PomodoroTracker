package com.bereznev.webapp.exception;
/*
    =====================================
    @project FlashCards
    @created 19/02/2023    
    @author Bereznev Nikita @CreativeWex
    =====================================
 */

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class TooBigArgumentException extends RuntimeException {

    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;

    public TooBigArgumentException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s: too big value (%s) for field %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}