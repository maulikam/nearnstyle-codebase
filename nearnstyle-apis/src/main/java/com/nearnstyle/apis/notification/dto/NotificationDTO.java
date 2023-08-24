package com.nearnstyle.apis.notification.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {

    private Long notificationId;

    private Long userId;  // reference to the user by its ID

    private Long salonId; // reference to the salon by its ID (can be null)

    private String message;

    private LocalDateTime dateTime;

    private NotificationStatus status;

    public enum NotificationStatus {
        SENT, FAILED, PENDING
    }
}

