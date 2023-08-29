package com.revanya.apps.services.service.service.repositories;


import com.revanya.apps.services.service.entities.Service;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;


@ApplicationScoped
public class ServiceRepository implements PanacheRepository<Service> {

    // Example custom queries:
    public Service findByServiceName(String serviceName) {
        return find("serviceName", serviceName).firstResult();
    }

    public List<Service> findByServiceCostLessThan(Double cost) {
        return list("serviceCost < ?1", cost);
    }

    public List<Service> findByIds(List<Long> ids) {
        return list("id IN ?1", ids);
    }

    public List<Service> findByName(String name) {
        return list("lower(serviceName) like lower(?1)", "%" + name + "%");
    }

    public List<Service> findByRatingAbove(Float threshold) {
        return list("rating > ?1", threshold);
    }

    public List<Service> findByDuration(Integer duration) {
        return list("duration = ?1", duration);
    }



    // ... Add other custom queries as required ...

}

