package com.guilhermeoliveira.honestmarket.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guilhermeoliveira.honestmarket.dto.CustomerDTO;
import com.guilhermeoliveira.honestmarket.entities.Customer;
import com.guilhermeoliveira.honestmarket.entities.Occupation;
import com.guilhermeoliveira.honestmarket.repositories.CustomerRepository;
import com.guilhermeoliveira.honestmarket.repositories.OccupationRepository;
import com.guilhermeoliveira.honestmarket.services.exception.ServiceException;

@Service
public class CustomerService {

	@Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OccupationRepository occupationRepository;

    @Transactional
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());

        Occupation occupation = occupationRepository.findById(customerDTO.getOccupationId())
                .orElseThrow(() -> new ServiceException("Occupation not found"));

        customer.setOccupation(occupation);

        Customer savedCustomer = customerRepository.save(customer);

        return convertToDTO(savedCustomer);
    }

    @Transactional
    public CustomerDTO updateCustomer(Long customerId, CustomerDTO updatedCustomerDTO) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ServiceException("Customer not found"));

        existingCustomer.setName(updatedCustomerDTO.getName());
        existingCustomer.setEmail(updatedCustomerDTO.getEmail());
        
        Occupation occupation = occupationRepository.findById(updatedCustomerDTO.getOccupationId())
                .orElseThrow(() -> new ServiceException("Occupation not found"));

        existingCustomer.setOccupation(occupation);

        Customer savedCustomer = customerRepository.save(existingCustomer);

        return convertToDTO(savedCustomer);
    }

    @Transactional(readOnly = true)
    public CustomerDTO getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ServiceException("Customer not found"));

        return convertToDTO(customer);
    }

    @Transactional(readOnly = true)
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteCustomer(Long customerId) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ServiceException("Customer not found"));

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
