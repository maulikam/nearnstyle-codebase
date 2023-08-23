package com.nearnstyle.apis.common.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class AuthenticationUser extends User  {

    private NearNStyleSecurityUser user;

    public AuthenticationUser(Long id, String name, String username, String password, Long roleId, String roleName,
                              String mobileNumber, String gender, String address, Date dob, boolean enabled, boolean accountNonExpired,
                              boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username == null ? "" : username,
                password == null ? "" : password,
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
                authorities == null ? new ArrayList<>() : authorities);
        user = new NearNStyleSecurityUser(id == null ? 0L : id,
                name == null ? "" : name,
                username == null ? "" : username,
                roleId == null ? 5L : roleId,
                roleName == null ? "" : roleName,
                mobileNumber == null ? "" : mobileNumber,
                gender == null ? "" : gender,
                dob == null ? new Date() : dob);

    }

    public NearNStyleSecurityUser getUser() {
        return user;
    }

    public void setUser(NearNStyleSecurityUser user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthenticationUser)) return false;
        if (!super.equals(o)) return false;
        AuthenticationUser that = (AuthenticationUser) o;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user);
    }
}
