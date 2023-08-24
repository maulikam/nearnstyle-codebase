package com.nearnstyle.apis.role.model;

import com.nearnstyle.apis.common.EntityAuditInfo;
import com.nearnstyle.apis.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ns_role_master")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role extends EntityAuditInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "description", nullable = true, length = 300)
    private String description;

    @OneToMany(mappedBy = "roleId", fetch = FetchType.LAZY)
    private List<User> userList;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", length = 255)
    private State state;

    @ManyToMany
    @JoinTable(
            name = "ns_role_features",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id"))
    private List<Feature> features;


    public enum State {
        ACTIVE,
        INACTIVE
    }

    public static class Fields {

        private Fields() {

        }

        public static final String ID = "id";
        public static final String DESCRIPTION = "description";
        public static final String NAME = "name";
        public static final String CODE = "code";
        public static final String MAX_POSITION = "maxPosition";
        public static final String USER_LIST = "userList";
        public static final String STATE = "state";
        public static final String TITLE = "title";
    }
}
