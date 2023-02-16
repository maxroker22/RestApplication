package ru.netology.restapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.restapplication.exception.InvalidCredentials;
import ru.netology.restapplication.exception.UnauthorizedUser;
import ru.netology.restapplication.model.Authorities;
import ru.netology.restapplication.service.AuthorizationService;

import java.util.List;

@RestController
public class AuthorizationController {
    @Autowired
    AuthorizationService service;

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user,
                                            @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }

    @ExceptionHandler(InvalidCredentials.class)
    ResponseEntity<String> resolveInvalidCredentialsException(InvalidCredentials ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    ResponseEntity<String> resolveUnauthorizedException(UnauthorizedUser ex) {
        System.out.println(ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }


}
