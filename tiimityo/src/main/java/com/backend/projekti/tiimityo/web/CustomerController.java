package com.backend.projekti.tiimityo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.projekti.tiimityo.domain.Customer;
import com.backend.projekti.tiimityo.domain.CustomerRepository;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customerlist")
    public String getCustomerList(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customerlist";
    }

    @GetMapping("/addcustomer")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer(null, "", ""));
        return "addcustomer";
    }

    @PostMapping("/savecustomer")
    public String saveCustomer(@RequestParam String username,
            @RequestParam String email,
            Model model) {

        boolean hasErrors = false;

        if (username == null || username.trim().isEmpty()) {
            model.addAttribute("errorUsername", "Käyttäjänimi on pakollinen");
            hasErrors = true;
        } else if (customerRepository.findByUsername(username) != null) {
            model.addAttribute("errorUsername", "Käyttäjänimi on jo käytössä");
            hasErrors = true;
        }

        if (email == null || email.trim().isEmpty()) {
            model.addAttribute("errorEmail", "Sähköposti on pakollinen");
            hasErrors = true;
        } else if (customerRepository.existsByEmail(email)) {
            model.addAttribute("errorEmail", "Sähköposti on jo käytössä");
            hasErrors = true;
        }

        if (hasErrors) {
            model.addAttribute("customer", new Customer(null, username, email));
            return "addcustomer";
        }

        Customer customer = new Customer(null, username, email);
        customerRepository.save(customer);

        return "redirect:/customerlist";
    }

    @GetMapping("/deletecustomer/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerRepository.deleteById(id);
        return "redirect:/customerlist";
    }

    @GetMapping("/editcustomer/{id}")
    public String editCustomer(@PathVariable("id") Long id, Model model) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID: " + id));
        model.addAttribute("customer", customer);
        return "editcustomer";
    }

    @PostMapping("/updatecustomer")
    public String updateCustomer(@ModelAttribute Customer customer, Model model) {
        customerRepository.save(customer);
        return "redirect:/customerlist";
    }

    @GetMapping("/cancelcustomeraction")
    public String cancelCustomerAction(Model model) {
        return "redirect:/customerlist";
    }
}
