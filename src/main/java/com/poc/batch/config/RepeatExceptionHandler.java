package com.poc.batch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.exception.ExceptionHandler;

@Slf4j
class RepeatExceptionHandler implements ExceptionHandler {

    @Override
    public void handleException(RepeatContext rc, Throwable throwable) {
        if (throwable instanceof FlatFileParseException) {
            FlatFileParseException fe = (FlatFileParseException) throwable;
            log.error("Parse exception at line# {} input is: \n---\n {} \n---\n", fe.getLineNumber(), fe.getInput());
        } else if (throwable instanceof ValidationException) {
            ValidationException ve = (ValidationException) throwable;
            log.error("Validation exception " + ve.getMessage()+"\n");
        }
    }
}