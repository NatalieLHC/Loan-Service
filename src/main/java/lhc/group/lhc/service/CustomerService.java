package lhc.group.lhc.service;

import lhc.group.lhc.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Customer addCustomer(Customer customer);
}
