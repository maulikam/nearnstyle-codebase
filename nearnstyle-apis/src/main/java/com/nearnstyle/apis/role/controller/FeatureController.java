package com.nearnstyle.apis.role.controller;

import com.nearnstyle.apis.role.dto.FeatureDTO;
import com.nearnstyle.apis.role.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/features")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @GetMapping
    public ResponseEntity<List<FeatureDTO>> getAllFeatures() {
        return ResponseEntity.ok(featureService.getAllFeatures());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeatureDTO> getFeatureById(@PathVariable(value = "id") Long featureId) {
        return ResponseEntity.ok(featureService.getFeatureById(featureId));
    }

    @PostMapping
    public ResponseEntity<FeatureDTO> createFeature(@RequestBody FeatureDTO featureDto) {
        return ResponseEntity.ok(featureService.createFeature(featureDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeatureDTO> updateFeature(@PathVariable(value = "id") Long featureId, @RequestBody FeatureDTO featureDto) {
        return ResponseEntity.ok(featureService.updateFeature(featureId, featureDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeature(@PathVariable(value = "id") Long featureId) {
        featureService.deleteFeature(featureId);
        return ResponseEntity.ok().build();
    }
}

