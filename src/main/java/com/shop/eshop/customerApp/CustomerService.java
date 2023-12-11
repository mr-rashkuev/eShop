package com.shop.eshop.customerApp;

import com.shop.eshop.customerApp.dto.CustomerRq;
import com.shop.eshop.customerApp.mapper.CustomerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public List<CustomerRq> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    public CustomerRq getCustomerById(Long id) {
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        return customerMapper.toDto(customer);
    }

    public void registerCustomer(CustomerRq customerRq) {
        CheckEmailExists(customerRq.getEmail());
        CheckPhoneNumberExists(customerRq.getPhoneNumber());
        CustomerEntity customer = customerMapper.toEntity(customerRq);
        customerRepository.save(customer);
    }

    public void updateCustomer(Long id, CustomerRq customerRq) {
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("customer with id " + id + " does not exist"));
        customer.setFirstName(customerRq.getFirstName());
        customer.setMiddleName(customerRq.getMiddleName());
        customer.setLastName(customerRq.getLastName());
        customer.setCity(customerRq.getCity());
        emailValidate(customer.getEmail(), customerRq.getEmail());
        customer.setEmail(customerRq.getEmail());
        customer.setPhoneNumber(customer.getPhoneNumber());
        phoneNumberValidate(customer.getPhoneNumber(), customerRq.getPhoneNumber());
        customer.setPhoneNumber(customer.getPhoneNumber());
        customerRepository.save(customer);
    }

    public void emailValidate(String oldEmail, String newEmail) {
        if (newEmail != null &&
                !Objects.equals(oldEmail, newEmail)) {
            CheckEmailExists(newEmail);
        }
    }

    public void phoneNumberValidate(String oldNumber, String newNumber) {
        if (newNumber != null &&
                !Objects.equals(oldNumber, newNumber)) {
            CheckEmailExists(newNumber);
        }
    }

    public void CheckEmailExists(String email) {
        if (customerRepository.getByEmail(email).isPresent()) {
            throw new BusinessException("This email already taken");
        }
    }

    public void CheckPhoneNumberExists(String phoneNumber) {
        if (customerRepository.getByPhoneNumber(phoneNumber).isPresent()) {
            throw new BusinessException("This phone number already taken");
        }
    }

}

