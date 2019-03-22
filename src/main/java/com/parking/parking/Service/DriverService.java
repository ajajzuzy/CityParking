package com.parking.parking.Service;

import com.parking.parking.Model.Driver;
import com.parking.parking.Model.ParkingEvent;
import com.parking.parking.Repository.DriverRepository;
import com.parking.parking.Repository.ParkingEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * DriverService's storing all methods about Driver
 */

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private ParkingEventRepository parkingEventRepository;

    /**
     * addNewDriver's saving a new Driver to database
     *
     * @param newDriver - object of new driver
     * @return drivers fields
     */
    public Driver addNewDriver(Driver newDriver) {
        ParkingEvent parkingEvent = new ParkingEvent();
        parkingEvent.setDriver(newDriver);
        driverRepository.save(newDriver);
        parkingEventRepository.save(parkingEvent);
        return newDriver;
    }

    /**
     * findById's finding drivers by drivers id
     *
     * @param driverId - driver's id
     * @return drivers fields or null when there is no driver witch such this id
     */
    public Driver findById(Integer driverId) {
        try {
            Driver driver = driverRepository.findById(driverId).get();
            return driver;
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
