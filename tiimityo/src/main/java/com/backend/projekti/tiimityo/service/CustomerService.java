package com.backend.projekti.tiimityo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backend.projekti.tiimityo.domain.Customer;
import com.backend.projekti.tiimityo.domain.CustomerRepository;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Get all customers. Throw error and message if failed.
    public List<Customer> getAllCustomers() {
        try {
            return (List<Customer>) customerRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all customers: " + e.getMessage());
        }
    }

    // Save customer. Throw error and message if failed.
    public Customer saveCustomer(Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save customer: " + e.getMessage());
        }
    }

    // Update customer. Throw error and message if failed.
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Optional<Customer> existingCustomerOpt = customerRepository.findById(id);
        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();
            existingCustomer.setUsername(updatedCustomer.getUsername());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            // Update other fields if necessary
            return customerRepository.save(existingCustomer);
        } else {
            throw new RuntimeException("Customer not found with ID: " + id);
        }
    }

    // Delete customer, return true if successful. Throw error and message if failed.
    public boolean deleteCustomer(Long id) {
        try {
            if (customerRepository.existsById(id)) {
                customerRepository.deleteById(id);
                return true;
            } else {
                throw new RuntimeException("Customer not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete customer: " + e.getMessage());
        }
    }

    // Fetch customer by ID, throw error if not found.
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
    }
}
