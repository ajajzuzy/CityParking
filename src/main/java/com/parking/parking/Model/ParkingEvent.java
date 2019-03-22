package com.parking.parking.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Locale;


/**
 * ParkingEvent's saving detailed information about parking event
 */
@Entity
@Table(name = "parking")
public class ParkingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private int id;

    @Column(name = "startTime")
    private LocalDateTime startTime;

    @Column(name = "finishTime")
    private LocalDateTime finishTime;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "currency")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    @JsonIgnore
    private Driver driver;

    public ParkingEvent() {
        this.currency = currency();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {

        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public int hoursParked() {
        double duration = Duration.between(startTime, finishTime).toMinutes() / 60.0;
        if (duration <= 1) {
            return 1;
        }
        return (int) Math.ceil(duration);
    }

    public Currency currency() {
        Currency currencyPLN = Currency.getInstance(new Locale("pl", "PL"));
        return currencyPLN;
    }
}
