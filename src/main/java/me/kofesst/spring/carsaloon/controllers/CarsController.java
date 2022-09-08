package me.kofesst.spring.carsaloon.controllers;

import me.kofesst.spring.carsaloon.models.Car;
import me.kofesst.spring.carsaloon.repositories.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cars")
public class CarsController {
    @Autowired
    private CarsRepository repository;

    @GetMapping
    public String carsMain(Model model) {
        Iterable<Car> cars = repository.findAll();
        model.addAttribute("models", cars);
        return "cars/main";
    }

    @PostMapping
    public String carsSearch(
            @RequestParam String brand,
            Model model
    ) {
        Iterable<Car> cars = repository.getByBrandContainsIgnoreCase(brand);
        model.addAttribute("brand", brand);
        model.addAttribute("models", cars);
        return "cars/main";
    }

    @GetMapping("/add")
    public String carsAdd() {
        return "cars/add";
    }

    @PostMapping("/add")
    public String carsAdd(
            @RequestParam String brand,
            @RequestParam String carModel,
            @RequestParam Integer maxSpeed,
            @RequestParam Double weight,
            @RequestParam Integer price
    ) {
        Car car = new Car(brand, carModel, maxSpeed, weight, price);
        repository.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/{id}")
    public String carDetails(
            @PathVariable("id") Long id,
            Model model
    ) {
        Optional<Car> car = repository.findById(id);
        if (car.isEmpty()) {
            return "redirect:/cars";
        }

        model.addAttribute("result", car.get());
        return "cars/details";
    }

    @GetMapping("/edit/{id}")
    public String carEdit(
            @PathVariable("id") Long id,
            Model model
    ) {
        Optional<Car> car = repository.findById(id);
        if (car.isEmpty()) {
            return "redirect:/cars";
        }

        model.addAttribute("edit", car.get());
        return "cars/add";
    }

    @PostMapping("/edit/{id}")
    public String carEdit(
            @PathVariable Long id,
            @RequestParam String brand,
            @RequestParam String carModel,
            @RequestParam Integer maxSpeed,
            @RequestParam Double weight,
            @RequestParam Integer price
    ) {
        Car car = repository.findById(id).orElseThrow();
        car.setBrand(brand);
        car.setModel(carModel);
        car.setMaxSpeed(maxSpeed);
        car.setWeight(weight);
        car.setPrice(price);
        repository.save(car);
        return "redirect:/cars/" + id;
    }

    @PostMapping("/delete/{id}")
    public String carDelete(
            @PathVariable Long id
    ) {
        repository.deleteById(id);
        return "redirect:/cars";
    }
}
