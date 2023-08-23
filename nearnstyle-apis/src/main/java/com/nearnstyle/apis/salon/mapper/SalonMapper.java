package com.nearnstyle.apis.salon.mapper;

import com.nearnstyle.apis.salon.dto.SalonDTO;
import com.nearnstyle.apis.salon.model.Salon;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SalonMapper{

    public static SalonDTO toSalonDTO(Salon salon) {
        if (salon == null) {
            return null;
        }
        SalonDTO dto = new SalonDTO();
        dto.setId(salon.getId());
        dto.setName(salon.getName());
        dto.setCode(salon.getCode());
        dto.setAddress(salon.getAddress());
        dto.setAd_state(salon.getAd_state());
        dto.setAd_district(salon.getAd_district());
        dto.setAd_city(salon.getAd_city());
        dto.setAd_pincode(salon.getAd_pincode());
        dto.setState(salon.getState().name());
        dto.setCodeGeneratedAt(salon.getCodeGeneratedAt());
        dto.setCodeExpiresAt(salon.getCodeExpiresAt());
        dto.setContactName(salon.getContactName());
        dto.setContactEmail(salon.getContactEmail());
        dto.setContactMobile(salon.getContactMobile());
        dto.setLatitude(salon.getLatitude());
        dto.setLongitude(salon.getLongitude());


        return dto;
    }

    public static Salon toSalon(SalonDTO dto, Salon salon) {
        if (dto == null) {
            return null;
        }
        salon.setId(dto.getId());
        salon.setName(dto.getName());
        salon.setCode(dto.getCode());
        salon.setAddress(dto.getAddress());
        salon.setAd_state(dto.getAd_state());
        salon.setAd_district(dto.getAd_district());
        salon.setAd_city(dto.getAd_city());
        salon.setAd_pincode(dto.getAd_pincode());
        salon.setState(Salon.State.valueOf(dto.getState()));
        salon.setCodeGeneratedAt(dto.getCodeGeneratedAt());
        salon.setCodeExpiresAt(dto.getCodeExpiresAt());
        salon.setContactName(dto.getContactName());
        salon.setContactEmail(dto.getContactEmail());
        salon.setContactMobile(dto.getContactMobile());


        salon.setLatitude(dto.getLatitude());
        salon.setLongitude(dto.getLongitude());

        return salon;
    }

    public static List<Salon> toSalons(List<SalonDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        List<Salon> entities = new ArrayList<>();
        for (SalonDTO dto : dtoList) {
            entities.add(toSalon(dto, new Salon()));
        }
        return entities;
    }

    public static List<SalonDTO> toSalonDTOs(List<Salon> entityList) {
        if (entityList == null) {
            return null;
        }
        List<SalonDTO> dtoList = new ArrayList<>();
        for (Salon entity : entityList) {
            dtoList.add(toSalonDTO(entity));
        }
        return dtoList;
    }

}


