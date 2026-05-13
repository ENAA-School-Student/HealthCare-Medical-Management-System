package com.healthcare.healthcare.controller;

import com.healthcare.healthcare.dto.AuthResponseDTO;
import com.healthcare.healthcare.dto.LoginRequestDTO;
import com.healthcare.healthcare.dto.RegisterRequestDTO;
import com.healthcare.healthcare.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponseDTO register(
            @Valid @RequestBody RegisterRequestDTO request
    ) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}