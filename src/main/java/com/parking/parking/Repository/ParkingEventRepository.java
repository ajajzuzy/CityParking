package com.parking.parking.Repository;

import com.parking.parking.Model.ParkingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;


public interface ParkingEventRepository extends JpaRepository<ParkingEvent, Integer> {

    @Query(value = "SELECT SUM(parkingEvent.value) FROM ParkingEvent parkingEvent WHERE parkingEvent.date = ?1 ")
    Double findAllByDate(LocalDate date);


    ParkingEvent findByDriverId(int driverId);
}
