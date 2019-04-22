package com.CentralBookStore.Bookstore.Bookstore.Service;

import com.CentralBookStore.Bookstore.Bookstore.DTO.Login;
import com.CentralBookStore.Bookstore.Bookstore.Model.Customer;
import com.CentralBookStore.Bookstore.Bookstore.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    @Transactional
    public Customer addCustomer(Customer customer){
        customerRepository.save(customer);
        return customer;
    }

    @Transactional
    public Customer updateCustomer(Customer customer){
        Customer customerUpdate = new Customer();
        customerUpdate = customerRepository.getOne(customer.getId());
        customerUpdate.setEmail(customer.getEmail());
        customerUpdate.setName(customer.getName());
        customerUpdate.setPassword(customer.getPassword());
        customerRepository.save(customerUpdate);
        return customerUpdate;
    }

    public List<Customer> findCustomers(){ return customerRepository.findAll(); }

    @Transactional
    public void deleteCostumer(UUID id) {
        Customer customer = customerRepository.getOne(id);
        customerRepository.delete(customer);
    }


    public UUID login(Login login){
        Customer customer = new Customer();
    if(customerRepository.existsByEmail(login.getEmail())){
        customer = customerRepository.findCustomerByEmail(login.getEmail());
    if(customer.getEmail().equals(login.getEmail()) && customer.getPassword().equals(login.getPassword())){
        return customer.getId();
    }
        }
        customer.setId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        return customer.getId();
    }

}
