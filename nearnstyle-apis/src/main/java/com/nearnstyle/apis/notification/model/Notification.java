package com.nearnstyle.apis.notification.model;

import com.nearnstyle.apis.salon.model.Salon;
import com.nearnstyle.apis.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salonId", nullable = true)
    private Salon salon;

    @Column(length = 500, nullable = false)
    private String message;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private NotificationStatus status;

    public enum NotificationStatus {
        SENT, FAILED, PENDING
    }
}

