package me.kofesst.spring.carsaloon.controllers;

import me.kofesst.spring.carsaloon.models.Car;
import me.kofesst.spring.carsaloon.repositories.CarsRepository;
import me.kofesst.spring.carsaloon.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/cars")
public class CarsController {
    @Autowired
    private CarsRepository repository;

    @Autowired
    private CustomersRepository customersRepository;

    @GetMapping
    public String carsMain(Model model, Principal principal) {
        Iterable<Car> cars = repository.findAll();
        model.addAttribute("models", cars);

        String username = principal.getName();
        System.out.println(username);

        return "cars/main";
    }

    @PostMapping
    public String carsSearch(@RequestParam String brand, Model model) {
        Iterable<Car> cars = repository.getByBrandContainsIgnoreCase(brand);
        model.addAttribute("brand", brand);
        model.addAttribute("models", cars);
        return "cars/main";
    }

    @GetMapping("/add")
    public String carsAdd(Car car, Model model) {
        model.addAttribute("car", car);
        return "cars/add";
    }

    @PostMapping("/add")
    public String carsAdd(@ModelAttribute("car") @Valid Car car,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            return "cars/add";
        }

        repository.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/{id}")
    public String carDetails(@PathVariable("id") Long id,
                             Model model) {
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

        model.addAttribute("edit", id);
        model.addAttribute("car", car.get());
        return "cars/add";
    }

    @PostMapping("/edit/{id}")
    public String carEdit(@PathVariable Long id,
                          @ModelAttribute("car") @Valid Car car,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("edit", id);
            return "cars/add";
        }

        repository.save(car);
        return "redirect:/cars/" + id;
    }

    @PostMapping("/delete/{id}")
    public String carDelete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/cars";
    }
}
