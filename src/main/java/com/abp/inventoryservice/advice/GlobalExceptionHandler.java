package com.abp.inventoryservice.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<String> handleLowInventory(UnsupportedOperationException e, WebRequest request) {
        // TODO need to reconsider on error return, so that there's a clear distinction compare to other unexpected error
        // where is it more to notice on low inventory
        log.debug("handleLowInventory : {}", request);
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException e, WebRequest request) {
        log.debug("handleBadRequest : {}", request);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleGenericError(RuntimeException e, WebRequest request) {
        log.debug("handleGenericError : {}", request);
        log.error("Unexpected error: " + e.getMessage());
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
