package me.kofesst.spring.carsaloon.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Это обязательное поле")
    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 2, max = 20, message = "Длина значения должна быть от 2 до 20")
    private String brand;

    @NotNull(message = "Это обязательное поле")
    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 2, max = 20, message = "Длина значения должна быть от 2 до 20")
    private String model;

    @Positive(message = "Число должно быть положительным")
    private Integer maxSpeed;

    @Positive(message = "Число должно быть положительным")
    @Max(value = 100000, message = "Слишком большой вес")
    private Double weight;

    @Positive(message = "Число должно быть положительным")
    private Integer price;

    public Car() {
    }

    public Car(String brand, String model, Integer maxSpeed, Double weight, Integer price) {
        this.brand = brand;
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return brand + " " + model;
    }
}
