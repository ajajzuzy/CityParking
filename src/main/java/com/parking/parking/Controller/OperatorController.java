package com.parking.parking.Controller;

import com.parking.parking.Model.ParkingEvent;
import com.parking.parking.Service.ParkingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * OperatorController is dedicated for Operators. This controller lets to operators find out if selected drivers has started parking
 */
@RestController
@RequestMapping("/operator")
public class OperatorController {

    @Autowired
    private ParkingEventService parkingEventService;

    /**
     * checkIfDriverStartedParking's checking if selected driver started parking
     *
     * @param driverId - driver's id
     * @return true - if driver started parking, false - if didn't started parking
     */
    @GetMapping("/checkDriver/{driverId}")
    public Boolean checkIfDriverStartedParking(@PathVariable int driverId) {
        try {
            ParkingEvent parkingEvent = parkingEventService.findByDriverId(driverId);
            if (parkingEvent.getStartTime() != null) {
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            return null;
        }


    }
}
