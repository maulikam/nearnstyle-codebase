package com.nearnstyle.apis.salon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperatingHoursDTO {

    private Long id;

    private Long salonId; // reference to the salon by its ID

    private DayOfWeek day;

    private LocalTime openingTime;

    private LocalTime closingTime;
}

