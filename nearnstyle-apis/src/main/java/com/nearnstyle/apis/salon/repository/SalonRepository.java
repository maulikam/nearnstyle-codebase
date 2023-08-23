package com.nearnstyle.apis.salon.repository;


import com.nearnstyle.apis.salon.model.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalonRepository extends JpaRepository<Salon, Long> {

    public Optional<Salon> findByName(String name);

    Optional<Salon> findByCode(String code);
}

