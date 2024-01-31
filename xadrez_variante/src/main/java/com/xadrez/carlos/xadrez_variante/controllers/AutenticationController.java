package com.xadrez.carlos.xadrez_variante.controllers;


import com.xadrez.carlos.xadrez_variante.user.AuthenticationDTO;
import com.xadrez.carlos.xadrez_variante.user.RegisterDTO;
import com.xadrez.carlos.xadrez_variante.user.User;
import com.xadrez.carlos.xadrez_variante.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dados){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.login(), dados.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO dados){
        if (this.repository.findByLogin(dados.login())!= null) return ResponseEntity.badRequest().build();
        
        String encryptedPassword = new BCryptPasswordEncoder().encode(dados.password());
        User newUser = new User(dados.login(), encryptedPassword, dados.role());
        
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    
    }   

}
