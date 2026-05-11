package com.healthcare.healthcare.controller;

import com.healthcare.healthcare.dto.RegisterRequestDTO;
import com.healthcare.healthcare.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(
            @Valid @RequestBody RegisterRequestDTO request
    ) {

        return authService.register(request);
    }
}