package com.nearnstyle.apis.user.model;

import com.nearnstyle.apis.common.EntityAuditInfo; 
import com.nearnstyle.apis.salon.model.Salon; 
import com.nearnstyle.apis.role.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "hc_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User extends EntityAuditInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @Column(name = "user_name", nullable = true, length = 30, unique = true)
    private String userName;

    @Column(name = "google_id", nullable = true, length = 50, unique = true)
    private String googleId;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnoreProperties("userList")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonIgnoreProperties("userList")
    private Salon salon;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "mobile_number", nullable = false, length = 10)
    private String mobileNumber;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "email_id", length = 150)
    private String emailId;

    @Column(name = "first_name", nullable = true, length = 100)
    private String firstName;

    @Column(name = "middle_name", length = 100)
    private String middleName;

    @Column(name = "last_name", nullable = true, length = 100)
    private String lastName;

    @Column(name = "gender", length = 15)
    private String gender;

    @Column(name = "password", length = 50)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

    @Column(name = "no_of_attempts")
    private Integer noOfAttempts;

    @Column(name = "address", length = 100)
    private String address;
 

    public enum State {
        ACTIVE,
        INACTIVE,
        UNVERIFIED,
        DISAPPROVED
    }

    public static class Fields {

        private Fields() {

        }

        public static final String ID = "id";
        public static final String USER_NAME = "userName";
        public static final String ROLE = "role";
        public static final String ROLE_ID = "roleId";
        public static final String EMAIL_ID = "emailId";
        public static final String MOBILE_NUMBER = "mobileNumber";
        public static final String FIRST_NAME = "firstName";
        public static final String MIDDLE_NAME = "middleName";
        public static final String LAST_NAME = "lastName";
        public static final String GENDER = "gender";
        public static final String DATE_OF_BIRTH = "dateOfBirth";
        public static final String PASSWORDSTR = "password";
        public static final String STATE = "state";
    }

}
