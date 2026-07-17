package com.healthcare.healthcare.controller;

import com.healthcare.healthcare.dto.DoctorRequestDTO;
import com.healthcare.healthcare.dto.DoctorResponseDTO;
import com.healthcare.healthcare.entity.User;
import com.healthcare.healthcare.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    private Long getAuthenticatedUserId() {
        org.springframework.security.core.@Nullable Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getId();
    }

    private String getAuthenticatedUserRole() {
        @Nullable Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getRole().name();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DoctorResponseDTO> addDoctor(@Valid @RequestBody DoctorRequestDTO dto){
        return ResponseEntity.ok(doctorService.addDoctor(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('PATIENT', 'DOCTOR', 'ADMIN')")
    @GetMapping
    public ResponseEntity<Page<DoctorResponseDTO>> listOfDoctors(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "10")int size,
                                                                 @RequestParam(defaultValue = "nom") String sortBy,
                                                                 @RequestParam(defaultValue = "asc") String sortDir){
        return ResponseEntity.ok(doctorService.listOfDoctors(page,size, sortBy, sortDir));
    }

    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(@Valid @PathVariable Long id , @RequestBody DoctorRequestDTO dto){

        Long authUserId = getAuthenticatedUserId();
        String authUserRole = getAuthenticatedUserRole();

        if(authUserRole.equals("ADMIN")){
            return ResponseEntity.ok(doctorService.updateDoctor(id,dto));
        }

        if(authUserRole.equals("DOCTOR")){
            if(!authUserId.equals(id)){
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body((DoctorResponseDTO) Map.of("message","Enter your correct Id"));

            }
            return ResponseEntity.ok(doctorService.updateDoctor(id,dto));
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body((DoctorResponseDTO) Map.of("message","Enter your correct Id"));

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<Page<DoctorResponseDTO>> searchDoctorBySpecialite(
            @RequestParam String specialite,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(
                doctorService.searchDoctorBySpecialite(specialite, page, size));
    }
    @GetMapping("/count")
    public ResponseEntity<Long> countPatients() {
        return ResponseEntity.ok(doctorService.countDoctors());
    }
}
