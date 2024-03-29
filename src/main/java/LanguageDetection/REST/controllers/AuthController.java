package LanguageDetection.REST.controllers;


import LanguageDetection.application.DTO.JwtResponseDTO;
import LanguageDetection.application.DTO.LoginRequestDTO;
import LanguageDetection.application.security.jwt.JwtUtils;
import LanguageDetection.domain.DomainService.UserDetailsDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

// fonte: https://bezkoder.com/spring-boot-jwt-authentication/

@CrossOrigin(origins = "http://localhost:5500/", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/LanguageDetection/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsDomainService userDetails = (UserDetailsDomainService) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponseDTO(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

}

