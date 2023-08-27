package com.guilhermeoliveira.honestmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.guilhermeoliveira.honestmarket.services.OccupationService;
import com.guilhermeoliveira.honestmarket.dto.OccupationDTO;

import java.util.List;

@RestController
@RequestMapping("/occupations")
public class OccupationController {

    private final OccupationService occupationService;

    @Autowired
    public OccupationController(OccupationService occupationService) {
        this.occupationService = occupationService;
    }

    @GetMapping
    public ResponseEntity<List<OccupationDTO>> getAllOccupations() {
        List<OccupationDTO> occupations = occupationService.getAllOccupations();
        return ResponseEntity.ok(occupations);
    }

    @GetMapping("/{occupationId}")
    public ResponseEntity<OccupationDTO> getOccupationById(@PathVariable Integer occupationId) {
        OccupationDTO occupation = occupationService.getOccupationById(occupationId);
        return ResponseEntity.ok(occupation);
    }

    @PostMapping
    public ResponseEntity<OccupationDTO> createOccupation(@RequestBody OccupationDTO occupationDTO) {
        OccupationDTO createdOccupation = occupationService.createOccupation(occupationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOccupation);
    }

    @PutMapping("/{occupationId}")
    public ResponseEntity<OccupationDTO> updateOccupation(
            @PathVariable Integer occupationId,
            @RequestBody OccupationDTO updatedOccupationDTO
    ) {
        OccupationDTO updatedOccupation = occupationService.updateOccupation(occupationId, updatedOccupationDTO);
        return ResponseEntity.ok(updatedOccupation);
    }

    @DeleteMapping("/{occupationId}")
    public ResponseEntity<Void> deleteOccupation(@PathVariable Integer occupationId) {
        occupationService.deleteOccupation(occupationId);
        return ResponseEntity.noContent().build();
    }
}

