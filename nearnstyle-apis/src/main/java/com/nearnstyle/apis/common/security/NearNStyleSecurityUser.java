package com.nearnstyle.apis.common.security;

import java.io.Serializable;
import java.util.Date;

public class NearNStyleSecurityUser implements Serializable {

    private Long id;
    private String name;
    private String userName;
    private Long roleId;
    private String roleName;
    private String mobileNumber;
    private String gender;
    private Date dob;

    public NearNStyleSecurityUser(Long id, String name, String username, Long roleId, String roleName,
                                   String mobileNumber, String gender, Date dob) {
        this.id = id;
        this.name = name;
        this.userName = username;
        this.roleId = roleId;
        this.roleName = roleName;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.dob = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

}
