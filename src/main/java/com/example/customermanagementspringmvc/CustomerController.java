package com.example.customermanagementspringmvc;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.CustomerService;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Customer> customers = customerService.findAll();
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/customers/view")
    public ModelAndView showCustomer(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("view");
        Customer customer = customerService.findByID(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping("/customers/view")
    public ModelAndView updateCustomer(@ModelAttribute Customer customer) {
        ModelAndView modelAndView = new ModelAndView("view");
        customerService.update(customer);
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "Customer updated successfully");
        return modelAndView;
    }
}
