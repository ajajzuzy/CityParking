package com.parking.parking.Service;

import com.parking.parking.Model.Driver;
import com.parking.parking.Model.ParkingEvent;
import com.parking.parking.Repository.DriverRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DriverServiceTest {
    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverService driverService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldCreateNewDriver() {
        //when
        when(driverRepository.save(any(Driver.class))).thenReturn(new Driver());
        Driver driver = new Driver();
        //then
        assertThat(driverService.addNewDriver(driver), is(notNullValue()));
    }

    @Test
    public void shouldFindDriversById() throws ParseException {
        //given
        int mockDriverId = 1;
        Driver mockDriver = new Driver(mockDriverId, "dfkt");
        when(driverRepository.findById(1)).thenReturn(Optional.of(mockDriver));
        //when
        Driver driver = driverService.findById(1);
        //then
        assertEquals(mockDriver.getRegistrationNumber(), driver.getRegistrationNumber());
    }

    @Test
    public void shouldCountFullTimeOfParking() {
        //given
        DateTimeFormatter simpleFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startingTime = LocalDateTime.parse("2019-03-09 12:21:28", simpleFormat);
        LocalDateTime finishTime = LocalDateTime.parse("2019-03-09 14:22:28", simpleFormat);
        ParkingEvent parkingEvent = new ParkingEvent();
        parkingEvent.setStartTime(startingTime);
        parkingEvent.setFinishTime(finishTime);
        int expected = 3;
        //when
        int hoursParked = parkingEvent.hoursParked();
        //then
        assertEquals(expected, hoursParked);

    }
}
