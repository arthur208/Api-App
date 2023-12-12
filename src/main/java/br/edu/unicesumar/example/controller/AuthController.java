package br.edu.unicesumar.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.example.config.auth.jwt.Jwt;
import br.edu.unicesumar.example.domain.Users;
import br.edu.unicesumar.example.dto.sign.SignIn;
import br.edu.unicesumar.example.dto.sign.SignUp;
import br.edu.unicesumar.example.service.UsersService;

@RestController
@CrossOrigin 
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/signin")
    public ResponseEntity<Jwt> signIn(@Valid @RequestBody SignIn signIn) {
        return ResponseEntity.ok(usersService.signIn(signIn));
    }
    @CrossOrigin 
    @PostMapping("/signup")
    public ResponseEntity<Users> signIn(@Valid @RequestBody SignUp signUp) {
        return ResponseEntity.ok(usersService.signUp(signUp));
    }

}
