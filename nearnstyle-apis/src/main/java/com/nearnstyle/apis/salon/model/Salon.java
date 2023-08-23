package com.nearnstyle.apis.salon.model;


import com.nearnstyle.apis.common.EntityAuditInfo;
import com.nearnstyle.apis.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hc_salon")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Salon extends EntityAuditInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "code", length = 20)
    private String code;

    @Column(name = "code_generated_at")
    private LocalDateTime codeGeneratedAt;

    @Column(name = "code_expires_at")
    private LocalDateTime codeExpiresAt;


    @OneToMany(mappedBy = "orgId", fetch = FetchType.LAZY)
    private List<User> userList;

    @Column(name = "address")
    private String address;

    @Column(name = "ad_state")
    private String ad_state;

    @Column(name = "ad_district")
    private String ad_district;

    @Column(name = "ad_city")
    private String ad_city;

    @Column(name = "ad_pincode")
    private Integer ad_pincode;



    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", length = 255)
    private State state;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_mobile")
    private String contactMobile;



    public enum State {
        ACTIVE,
        INACTIVE
    }

    public static class Fields {

        private Fields() {

        }

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String CODE = "code";
        public static final String ADDRESS = "address";
        public static final String AD_STATE = "ad_state";
        public static final String AD_CITY = "ad_city";
        public static final String AD_DISTRICT = "ad_district";
        public static final String AD_PINCODE = "ad_pincode";
        public static final String USER_LIST = "userList";
        public static final String STATE = "state";
        public static final String TITLE = "title";
    }
}
