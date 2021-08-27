package org.gosha.kalosha.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthExceptionController
{
    @GetMapping("forbidden")
    public ResponseEntity<Map<String, String>> handleForbidden()
    {
        return new ResponseEntity<>(Map.of("error", "Unauthorized"), HttpStatus.UNAUTHORIZED);
    }
}
