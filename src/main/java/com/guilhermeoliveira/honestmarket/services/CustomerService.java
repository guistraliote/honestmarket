package com.guilhermeoliveira.honestmarket.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilhermeoliveira.honestmarket.dto.CustomerDTO;
import com.guilhermeoliveira.honestmarket.entities.Customer;
import com.guilhermeoliveira.honestmarket.repositories.CustomerRepository;
import com.guilhermeoliveira.honestmarket.services.exception.CustomerServiceException;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());

        Customer savedCustomer = customerRepository.save(customer);

        return convertToDTO(savedCustomer);
    }

    public CustomerDTO updateCustomer(Long customerId, CustomerDTO updatedCustomerDTO) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerServiceException("Customer not found"));

        // Update customer details
        existingCustomer.setName(updatedCustomerDTO.getName());
        existingCustomer.setEmail(updatedCustomerDTO.getEmail());

        Customer savedCustomer = customerRepository.save(existingCustomer);

        return convertToDTO(savedCustomer);
    }

    public CustomerDTO getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerServiceException("Customer not found"));

        return convertToDTO(customer);
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteCustomer(Long customerId) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerServiceException("Customer not found"));

        customerRepository.delete(existingCustomer);
    }

    private CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        return customerDTO;
    }
}
