package com.parking.parking.Controller;


import com.parking.parking.Model.ParkingEvent;
import com.parking.parking.Service.ParkingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * OwnerController is dedicated for Owners. This controller lets to owners find out how much many was earned during a day
 */

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private ParkingEventService paymentService;

    /**
     * checkEarnedDuringDay's checking how much money was earned during a given day
     *
     * @param date - date of day witch Owner wants to check
     * @return - value of earned during a given day
     */
    @PostMapping("/checkEarned")
    public double checkEarnedDuringDay(@RequestBody ParkingEvent date) {
        LocalDate date1 = date.getDate();
        return paymentService.checkEarned(date1);
    }
}
