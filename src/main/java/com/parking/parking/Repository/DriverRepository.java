package com.parking.parking.Repository;

import com.parking.parking.Model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
}
