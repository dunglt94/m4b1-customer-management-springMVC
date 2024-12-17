package com.example.customermanagementspringmvc;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.CustomerService;
import service.ICustomerService;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public String showList(ModelMap model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "index";
    }

    @GetMapping("/{id}/view")
    public ModelAndView showCustomer(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("view");
        Customer customer = customerService.findByID(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping("/{id}")
    public String updateCustomer(Customer customer) {
        customerService.update(customer);
        return "redirect:/customers";
    }
}
