package com.guilhermeoliveira.honestmarket.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guilhermeoliveira.honestmarket.dto.OccupationDTO;
import com.guilhermeoliveira.honestmarket.entities.Occupation;
import com.guilhermeoliveira.honestmarket.repositories.OccupationRepository;
import com.guilhermeoliveira.honestmarket.services.exception.ServiceException;

@Service
public class OccupationService {

	private final OccupationRepository occupationRepository;

    @Autowired
    public OccupationService(OccupationRepository occupationRepository) {
        this.occupationRepository = occupationRepository;
    }
    
    @Transactional
    public OccupationDTO createOccupation(OccupationDTO occupationDTO) {
        Occupation occupation = new Occupation();
        occupation.setOccupation(occupationDTO.getOccupation());
        occupation.setDescription(occupationDTO.getDescription());

        Occupation savedOccupation = occupationRepository.save(occupation);

        return convertToDTO(savedOccupation);
    }

    @Transactional
    public OccupationDTO updateOccupation(Integer occupationId, OccupationDTO updatedOccupationDTO) {
        Occupation existingOccupation = occupationRepository.findById(occupationId)
                .orElseThrow(() -> new ServiceException("Occupation not found"));

        existingOccupation.setOccupation(updatedOccupationDTO.getOccupation());
        existingOccupation.setDescription(updatedOccupationDTO.getDescription());

        Occupation savedOccupation = occupationRepository.save(existingOccupation);

        return convertToDTO(savedOccupation);
    }

    @Transactional
    public void deleteOccupation(Integer occupationId) {
        Occupation existingOccupation = occupationRepository.findById(occupationId)
                .orElseThrow(() -> new ServiceException("Occupation not found"));

        occupationRepository.delete(existingOccupation);
    }

    @Transactional(readOnly = true)
    public OccupationDTO getOccupationById(Integer occupationId) {
        Occupation occupation = occupationRepository.findById(occupationId)
                .orElseThrow(() -> new ServiceException("Occupation not found"));

        return convertToDTO(occupation);
    }

    @Transactional(readOnly = true)
    public List<OccupationDTO> getAllOccupations() {
        List<Occupation> occupations = occupationRepository.findAll();
        return occupations.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    private OccupationDTO convertToDTO(Occupation occupation) {
    	OccupationDTO occupationDTO = new OccupationDTO();
    	occupationDTO.setId(occupation.getId());
    	occupationDTO.setOccupation(occupation.getOccupation());
    	occupationDTO.setDescription(occupation.getDescription());
        return occupationDTO;
    }
}
