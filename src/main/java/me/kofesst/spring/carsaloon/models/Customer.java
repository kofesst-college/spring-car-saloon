package me.kofesst.spring.carsaloon.models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerFirstname;
    private String customerLastname;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carId", referencedColumnName = "id")
    private Car car;

    private Date timestamp;
    private Boolean isOnline;

    public Customer() {
    }

    public Customer(String customerFirstname, String customerLastname, Car car, Boolean isOnline) {
        this.customerFirstname = customerFirstname;
        this.customerLastname = customerLastname;
        this.car = car;
        this.isOnline = isOnline;
        this.timestamp = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerFirstname() {
        return customerFirstname;
    }

    public void setCustomerFirstname(String customerFirstname) {
        this.customerFirstname = customerFirstname;
    }

    public String getCustomerLastname() {
        return customerLastname;
    }

    public void setCustomerLastname(String customerLastname) {
        this.customerLastname = customerLastname;
    }

    public String getCustomer() {
        return customerFirstname + " " + customerLastname;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getCarDetails() {
        return car.getBrand() + " " + car.getModel();
    }

    public String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(timestamp);
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }
}
