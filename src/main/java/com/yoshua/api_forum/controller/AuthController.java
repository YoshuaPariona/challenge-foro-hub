package com.yoshua.api_forum.controller;

import com.yoshua.api_forum.domain.user.AuthData;
import com.yoshua.api_forum.domain.user.User;
import com.yoshua.api_forum.infrastructure.security.TokenJWTData;
import com.yoshua.api_forum.infrastructure.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping
    public ResponseEntity<TokenJWTData> login(@RequestBody @Valid AuthData authData) {
        var authToken = new UsernamePasswordAuthenticationToken(authData.email(), authData.password());
        var auth = authManager.authenticate(authToken);
        var tokenJWT = tokenService.generateToken((User) auth.getPrincipal()) ;
        return ResponseEntity.ok(new TokenJWTData(tokenJWT));
    }
}
