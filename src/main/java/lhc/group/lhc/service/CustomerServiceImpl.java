package lhc.group.lhc.service;

import lhc.group.lhc.entity.Customer;
import lhc.group.lhc.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository=customerRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Customer addCustomer(Customer customer) {
        customer.setCustomerId(null);
        return customerRepository.save(customer);
    }
}
