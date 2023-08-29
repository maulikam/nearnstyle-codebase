package com.revanya.apps.services.authentication;

import com.revanya.apps.services.user.entities.User;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Getter
@Setter
@Table(name = "otp")
public class OtpEntity extends PanacheEntity {

    @Column(unique = true, nullable = false)
    private String mobileNumber;

    private String otp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

}
