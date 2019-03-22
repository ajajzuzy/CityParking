package com.parking.parking.Service;


import com.parking.parking.Model.DriverType;
import com.parking.parking.Model.ParkingEvent;
import com.parking.parking.Repository.ParkingEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.NoSuchElementException;


/**
 * ParkingEventService's storing all methods about ParkingEventService's
 */
@Service
public class ParkingEventService {

    @Autowired
    private ParkingEventRepository parkingEventRepository;


    private HashMap<DriverType, Double> fareMultipliers = new HashMap<>();

    public ParkingEventService() {
        fareMultipliers.put(DriverType.Regular, 1.5);
        fareMultipliers.put(DriverType.Vip, 1.2);
    }

    /**
     * findById's finding drivers by drivers id
     *
     * @param driverId - driver's id
     * @return driver object
     */
    public ParkingEvent findByDriverId(int driverId) {
        try {
            ParkingEvent parkingEvent = parkingEventRepository.findByDriverId(driverId);
            return parkingEvent;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * startParking's saving actual time in database as starting time of parking
     *
     * @param driverId - driver's id
     * @return actual time
     */
    public LocalDateTime startParking(int driverId) {
        try {
            ParkingEvent parkingEvent = findByDriverId(driverId);
            LocalDateTime startTime = LocalDateTime.now();
            parkingEvent.setStartTime(startTime);
            parkingEventRepository.save(parkingEvent);
            return startTime;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * finishParking's saving actual time in database as finish time of parking.
     *
     * @param driverId - driver's id
     * @return full time of parking car
     */
    public Integer finishParking(int driverId) {
        try {
            ParkingEvent parkingEvent = parkingEventRepository.findByDriverId(driverId);
            LocalDateTime finishTime = LocalDateTime.now();
            parkingEvent.setFinishTime(finishTime);
            parkingEvent.setDate(finishTime.toLocalDate());
            parkingEventRepository.save(parkingEvent);
            return parkingEvent.hoursParked();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * calculatePayment's counting value of pay depends of driver type
     *
     * @param hoursParked - is full time of parking car [hours]
     * @param driverType  - type of driver (Regular or Vip)
     * @return value of earned during a given day
     */
    public BigDecimal calculatePayment(int hoursParked, DriverType driverType) {

        BigDecimal howMuchToPay = new BigDecimal("0");
        if (driverType == DriverType.Regular) {
            howMuchToPay = new BigDecimal("1");
        }
        if (hoursParked == 2) {
            howMuchToPay = new BigDecimal("2");
        }
        if (hoursParked >= 3) {

            howMuchToPay = new BigDecimal(2 * Math.pow(fareMultipliers.get(driverType), (hoursParked - 2)));
        }
        return howMuchToPay;
    }

    /**
     * checkEarned's counting how much money was earned during a given day
     *
     * @param date - is selected day
     * @return value of earned during a given day
     */
    public Double checkEarned(LocalDate date) {
        try {
            return parkingEventRepository.findAllByDate(date);
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * payment's counting how much money driver has to pay. Also added value of pay to database
     *
     * @param driverId - driver's id
     * @return how much money driver has to pay
     */
    public BigDecimal payment(int driverId) {
        try {
            ParkingEvent parkingEvent = parkingEventRepository.findByDriverId(driverId);
            BigDecimal calculateValueToPay = calculatePayment(parkingEvent.hoursParked(), parkingEvent.getDriver().getDriverType());
            parkingEvent.setValue(calculateValueToPay);
            parkingEventRepository.save(parkingEvent);
            return calculateValueToPay;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * getCurrency's getting currency of payments
     *
     * @param driverId - driver's id
     * @return currency of payments
     */
    public String getCurrency(int driverId) {
        return parkingEventRepository.findByDriverId(driverId).getCurrency().toString();
    }
}
