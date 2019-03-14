package com.parking.parking.Model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * Driver's saving basic information about Driver
 */

@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "driver_id")
    private Integer id;

    /**
     * @param registrationNumber - registration number of parking vehicle
     */
    @Column(name = "registration_number")
    private String registrationNumber;

    /**
     * @param driverType - type of Drivers : Regular or Vip
     */
    @Column
    private DriverType driverType;


    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ParkingEvent> parkingEventSet = new HashSet<>();


    public Driver() {
    }

    public Driver(Integer id, String registrationNumber) {
        this.id = id;
        this.registrationNumber = registrationNumber;
    }

    public Driver(Integer id, DriverType driverType) {
        this.id = id;
        this.driverType = driverType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Set<ParkingEvent> getParkingEventSet() {
        return parkingEventSet;
    }

    public void setParkingEventSet(Set<ParkingEvent> parkingEventSet) {
        this.parkingEventSet = parkingEventSet;
    }

}
