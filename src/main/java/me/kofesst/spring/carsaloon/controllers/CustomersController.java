package me.kofesst.spring.carsaloon.controllers;

import me.kofesst.spring.carsaloon.models.Car;
import me.kofesst.spring.carsaloon.models.Customer;
import me.kofesst.spring.carsaloon.repositories.CarsRepository;
import me.kofesst.spring.carsaloon.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomersController {
    @Autowired
    private CarsRepository carsRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @GetMapping("/customers")
    public String customersMain(Model model) {
        Iterable<Customer> customers = customersRepository.findAll();
        model.addAttribute("models", customers);
        return "customers/main";
    }

    @PostMapping("/customers")
    public String customersSearch(
            @RequestParam String customer,
            Model model
    ) {
        Iterable<Customer> customers = customersRepository.getByCustomerFirstnameContainsIgnoreCase(
                customer
        );
        model.addAttribute("customer", customer);
        model.addAttribute("models", customers);
        return "customers/main";
    }

    @GetMapping("/customers/add")
    public String customersAdd(Model model) {
        Iterable<Car> cars = carsRepository.findAll();
        model.addAttribute("cars", cars);
        return "customers/add";
    }

    @PostMapping("/customers/add")
    public String customersAdd(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam Long carId,
            @RequestParam(required = false, defaultValue = "false") Boolean isOnline
    ) {
        Car car = carsRepository.findById(carId).get();
        Customer customer = new Customer(firstname, lastname, car, isOnline);
        customersRepository.save(customer);
        return "redirect:/customers";
    }
}
