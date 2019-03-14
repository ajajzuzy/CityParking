package com.parking.parking.Service;


import com.parking.parking.Model.DriverType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;


import static org.junit.Assert.*;


public class PaymentServiceTest {

    @InjectMocks
    private ParkingEventService parkingEventService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnValueToPayAsRegularDriver() {
        //given
        BigDecimal expected = new BigDecimal("6.75");
        //when
        BigDecimal payment = parkingEventService.calculatePayment(5, DriverType.Regular);
        //then
        assertEquals(expected, payment);
    }

    @Test
    public void shouldReturnValueToPayAsVipDriver() {
        //given
        BigDecimal expected = new BigDecimal("0");
        //when
        BigDecimal payment = parkingEventService.calculatePayment(1, DriverType.Vip);
        //then
        assertEquals(expected, payment);
    }
}
