package com.nearnstyle.apis.salon.service.impl;

import com.nearnstyle.apis.salon.dto.SalonDTO;
import com.nearnstyle.apis.salon.mapper.SalonMapper;
import com.nearnstyle.apis.salon.model.Salon;
import com.nearnstyle.apis.salon.repository.SalonRepository;
import com.nearnstyle.apis.salon.service.SalonService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SalonServiceImpl implements SalonService {

    @Autowired
    private SalonRepository salonRepository;

    @Autowired
    private SalonMapper salonMapper;

    @Override
    public List<SalonDTO> getAllSalons() {
        List<Salon> salons = salonRepository.findAll();
        return salonMapper.toSalonDTOs(salons);
    }

    @Override
    public SalonDTO getSalonById(Long id) {
        Optional<Salon> salon = salonRepository.findById(id);
        if (salon.isPresent()) {
            return salonMapper.toSalonDTO(salon.get());
        }
        return null;
    }

    @Override
    public SalonDTO createSalon(SalonDTO salonDto) {

        Optional<Salon> salonOptional = salonRepository.findByName(salonDto.getName());
        if (salonOptional.isPresent()) {
            throw new IllegalStateException("Salon already exists");
        }

        Salon salon = new Salon();
        salon= salonMapper.toSalon(salonDto, salon);
        if (salon.getCode() == null) {
            // generate a new code
            String code = generateSalonCode(salon.getName());

            LocalDateTime now = LocalDateTime.now();
            salon.setCode(code);
            salon.setCodeGeneratedAt(now);
            salon.setCodeExpiresAt(now.plusHours(1)); // Set the expiry time to 1 hour from now
        }
        return salonMapper.toSalonDTO(salonRepository.save(salon));
    }


    @Override
    public SalonDTO updateSalon(Long id, SalonDTO salonDto) {
        Optional<Salon> salon = salonRepository.findById(id);
        if (salon.isPresent()) {
            Salon updatedSalon = salonMapper.toSalon(salonDto, salon.get());
            return salonMapper.toSalonDTO(salonRepository.save(updatedSalon));
        }
        return null;
    }

    @Override
    public boolean deleteSalon(Long id) {
        Optional<Salon> salon = salonRepository.findById(id);
        if (salon.isPresent()) {
            salonRepository.delete(salon.get());
            return true;
        }
        return false;
    }

    public String generateAndSetSalonCode(Long salonId) {
        Optional<Salon> optionalSalon = salonRepository.findById(salonId);
        if (optionalSalon.isPresent()) {
            Salon salon = optionalSalon.get();
            LocalDateTime now = LocalDateTime.now();

            // If code is already generated and it's not expired, return it
            if (salon.getCode() != null && salon.getCodeExpiresAt().isAfter(now)) {
                return salon.getCode();
            }

            // Otherwise, generate a new code
            String code = generateSalonCode(salon.getName());

            salon.setCode(code);
            salon.setCodeGeneratedAt(now);
            salon.setCodeExpiresAt(now.plusHours(1)); // Set the expiry time to 1 hour from now

            salonRepository.save(salon);

            return code;
        } else {
            // Handle the case where there is no salon with the given ID
            // For example, throw an exception
            throw new EntityNotFoundException("Salon with id " + salonId + " was not found");
        }
    }



    public boolean verifySalonCode(Long salonId, String code) {
        Salon salon = salonRepository.findById(salonId)
                .orElseThrow(() -> new EntityNotFoundException("Salon not found"));

        if (salon.getCode().equals(code) && LocalDateTime.now().isBefore(salon.getCodeExpiresAt())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public SalonDTO getSalonByCode(String code) {
        Optional<Salon> salon = salonRepository.findByCode(code);
        if (salon.isPresent()) {
            return salonMapper.toSalonDTO(salon.get());
        }

        throw new EntityNotFoundException("Salon with code " + code + " was not found");
    }

    private String generateSalonCode(String orgName) {
        String prefix = orgName.substring(0, Math.min(orgName.length(), 4)).toUpperCase();
        String randomSuffix = RandomStringUtils.randomAlphanumeric(9 - prefix.length());
        return prefix + randomSuffix;
    }


}



