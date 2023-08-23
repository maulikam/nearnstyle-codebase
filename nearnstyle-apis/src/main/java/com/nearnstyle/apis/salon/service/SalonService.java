package com.nearnstyle.apis.salon.service;

import com.nearnstyle.apis.salon.dto.SalonDTO;

import java.util.List;



public interface SalonService {

    List<SalonDTO> getAllSalons();

    SalonDTO getSalonById(Long id);

    SalonDTO createSalon(SalonDTO salonDto);

    SalonDTO updateSalon(Long id, SalonDTO salonDto);

    boolean deleteSalon(Long id);

    String generateAndSetSalonCode(Long salonId);

     boolean verifySalonCode(Long salonId, String code);

     SalonDTO getSalonByCode(String code);
}


