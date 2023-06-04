package pl.cardealership.business;

import lombok.AllArgsConstructor;
import pl.cardealership.business.DAO.CustomerDAO;
import pl.cardealership.infrastructure.database.entity.CustomerEntity;

import java.util.Optional;

@AllArgsConstructor
public class CustomerService {

    private final CustomerDAO customerDAO;
    public void issueInvoice(CustomerEntity customer) {
        customerDAO.issueInvoice(customer);
    }

    public CustomerEntity findCustomer(String email) {
        final Optional<CustomerEntity> customerByPesel = customerDAO.findByEmail(email);
        if (customerByPesel.isEmpty()) {
            throw new RuntimeException("Could not find Salesman by email: [%s]".formatted(email));
        }
        return customerByPesel.get();
    }
}
