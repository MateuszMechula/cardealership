package pl.cardealership.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cardealership.business.DAO.CustomerDAO;
import pl.cardealership.domain.Customer;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerDAO customerDAO;

    @Transactional
    public void issueInvoice(Customer customer) {
        customerDAO.issueInvoice(customer);
    }

    @Transactional
    public Customer findCustomer(String email) {
        final Optional<Customer> customerByPesel = customerDAO.findByEmail(email);
        if (customerByPesel.isEmpty()) {
            throw new RuntimeException("Could not find Salesman by email: [%s]".formatted(email));
        }
        return customerByPesel.get();
    }
    @Transactional
    public void saveServiceRequest(Customer customer) {
        customerDAO.saveServiceRequest(customer);
    }

    @Transactional
    public Customer saveCustomer(Customer customer) {
        return customerDAO.saveCustomer(customer);
    }
}
