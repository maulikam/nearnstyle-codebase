package com.nearnstyle.apis.salon.controller;

import com.nearnstyle.apis.salon.dto.SalonDTO;
import com.nearnstyle.apis.salon.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/salons")
public class SalonController {

    @Autowired
    private SalonService salonService;

    @PostMapping("/register")
    public ResponseEntity<Object> createSalonRegistration(@RequestBody SalonDTO salonDto) {
        try {
            return ResponseEntity.ok(salonService.createSalon(salonDto));
        } catch (IllegalStateException expected) {
            return ResponseEntity.badRequest().body("Salon already exists!");
        }
    }

    @GetMapping
    public ResponseEntity<List<SalonDTO>> getAllSalons() {
        return ResponseEntity.ok(salonService.getAllSalons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalonDTO> getSalonById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(salonService.getSalonById(id));
    }

    @PostMapping
    public ResponseEntity<Object> createSalon(@RequestBody SalonDTO salonDto) {
        try {
        return ResponseEntity.ok(salonService.createSalon(salonDto));
        } catch (IllegalStateException expected) {
            return ResponseEntity.badRequest().body("Salon already exists!");
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<SalonDTO> updateSalon(@PathVariable(value = "id") Long id, @RequestBody SalonDTO salonDto) {

            return ResponseEntity.ok(salonService.updateSalon(id, salonDto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSalon(@PathVariable Long id) {
        return ResponseEntity.ok(salonService.deleteSalon(id));
    }

    @PostMapping("/generateCode/{id}")
    public ResponseEntity<String> generateSalonCode(@PathVariable Long id) {
        try {
            String code = salonService.generateAndSetSalonCode(id);
            return ResponseEntity.ok(code);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating salon code");
        }
    }

    @GetMapping("/{id}/verifyCode/{code}")
    public ResponseEntity<Boolean> verifySalonCode(@PathVariable("id") Long salonId, @PathVariable("code") String code) {
        try {
            boolean isValid = salonService.verifySalonCode(salonId, code);
            return ResponseEntity.ok(isValid);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @GetMapping("/salon/{orgCode}")
    public ResponseEntity<SalonDTO> getSalonByCode(@PathVariable("orgCode") String orgCode) {
        return ResponseEntity.ok(salonService.getSalonByCode(orgCode));
    }



}
