package com.revanya.apps.services.authentication;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OtpRepository implements PanacheRepositoryBase<OtpEntity, Long> {

    public OtpEntity findByMobileNumber(String mobileNumber) {
        return find("mobileNumber", mobileNumber).firstResult();
    }
}

