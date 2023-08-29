package com.revanya.apps.services.notification.entities;

import com.revanya.apps.services.user.entities.User;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User recipient;

    private String title; // Title or header of the notification

    @Column(length = 500) // Adjust size as needed
    private String content; // Content or body of the notification

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSent;

    private Boolean isRead = false; // Flag to check if notification has been read

    private String type; // Could be "reminder", "update", "offer", etc.

    // Possible link to navigate the user somewhere when they tap/click on the notification
    private String actionURL;

    // Enum for notification type (if necessary)
    public enum NotificationType {
        REMINDER,
        UPDATE,
        OFFER,
        BOOKING_CONFIRMATION,
        CANCELLATION
    }
}

