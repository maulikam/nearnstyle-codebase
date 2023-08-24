package com.nearnstyle.apis.appointment.mapper;

import com.nearnstyle.apis.appointment.dto.AppointmentDTO;
import com.nearnstyle.apis.appointment.model.Appointment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    AppointmentDTO toDTO(Appointment appointment);

    Appointment fromDTO(AppointmentDTO appointmentDTO);

    List<AppointmentDTO> toDTOs(List<Appointment> appointments);

    List<Appointment> fromDTOs(List<AppointmentDTO> appointmentDTOs);
}

