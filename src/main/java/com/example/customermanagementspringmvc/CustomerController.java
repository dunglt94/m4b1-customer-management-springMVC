package com.example.customermanagementspringmvc;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

    @GetMapping("/create")
    public String showCreateForm(ModelMap model) {
        model.addAttribute("customer", new Customer());
        return "create";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, ModelMap model) {
        customerService.findByID(id);
        model.addAttribute("customer", customerService.findByID(id));
        return "update";
    }

    @PostMapping("/update")
    public String updateCustomer(Customer customer, RedirectAttributes redirectAttributes) {
        customerService.update(customer);
        redirectAttributes.addFlashAttribute("success", "Updated Customer Successfully");
        return "redirect:/customers";
    }

    @GetMapping("/{id}/delete")
    public String showDeleteForm(@PathVariable int id, ModelMap model) {
        customerService.findByID(id);
        model.addAttribute("customer", customerService.findByID(id));
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(Customer customer, RedirectAttributes redirectAttributes) {
        customerService.remove(customer.getId());
        redirectAttributes.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/customers";
    }
}
