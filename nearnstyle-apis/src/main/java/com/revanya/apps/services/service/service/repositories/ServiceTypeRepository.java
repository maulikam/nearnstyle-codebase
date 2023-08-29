package com.revanya.apps.services.service.service.repositories;


import com.revanya.apps.services.service.entities.ServiceType;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ServiceTypeRepository implements PanacheRepository<ServiceType> {

    // Example custom queries:
    public ServiceType findByName(String name) {
        return find("name", name).firstResult();
    }

    public List<ServiceType> findByAvgDurationLessThan(Integer duration) {
        return list("avgDuration < ?1", duration);
    }

    // ... Add other custom queries as required ...

}

