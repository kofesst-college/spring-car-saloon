package me.kofesst.spring.carsaloon.controllers;

import me.kofesst.spring.carsaloon.models.Car;
import me.kofesst.spring.carsaloon.models.Customer;
import me.kofesst.spring.carsaloon.repositories.CarsRepository;
import me.kofesst.spring.carsaloon.repositories.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomersController {
    @Autowired
    private CarsRepository carsRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @GetMapping
    public String customersMain(Model model) {
        Iterable<Customer> customers = customersRepository.findAll();
        model.addAttribute("models", customers);
        return "customers/main";
    }

    @PostMapping
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

    @GetMapping("/add")
    public String customersAdd(Customer customer, Model model) {
        model.addAttribute("customer", customer);

        Iterable<Car> cars = carsRepository.findAll();
        model.addAttribute("cars", cars);
        return "customers/add";
    }

    @PostMapping("/add")
    public String customersAdd(
            @ModelAttribute("customer") @Valid Customer customer,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            Iterable<Car> cars = carsRepository.findAll();
            model.addAttribute("cars", cars);
            return "customers/add";
        }

        customer.setTimestamp(new Date());
        customersRepository.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}")
    public String customerDetails(
            @PathVariable("id") Long id,
            Model model
    ) {
        Optional<Customer> customer = customersRepository.findById(id);
        if (customer.isEmpty()) {
            return "redirect:/customers";
        }

        model.addAttribute("result", customer.get());
        return "customers/details";
    }

    @GetMapping("/edit/{id}")
    public String customerEdit(@PathVariable("id") Long id, Model model) {
        Optional<Customer> customer = customersRepository.findById(id);
        if (customer.isEmpty()) {
            return "redirect:/customers";
        }

        model.addAttribute("edit", id);
        model.addAttribute("customer", customer.get());

        Iterable<Car> cars = carsRepository.findAll();
        model.addAttribute("cars", cars);

        return "customers/add";
    }

    @PostMapping("/edit/{id}")
    public String customerEdit(
            @PathVariable Long id, @ModelAttribute("customer") @Valid Customer customer,
            BindingResult result, Model model
    ) {
        if (result.hasErrors()) {
            Iterable<Car> cars = carsRepository.findAll();
            model.addAttribute("edit", id);
            model.addAttribute("cars", cars);
            return "customers/add";
        }

        customer.setTimestamp(new Date());
        customersRepository.save(customer);
        return "redirect:/customers/" + id;
    }

    @PostMapping("/delete/{id}")
    public String customerDelete(
            @PathVariable Long id
    ) {
        customersRepository.deleteById(id);
        return "redirect:/customers";
    }
}
