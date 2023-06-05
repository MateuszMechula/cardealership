package pl.cardealership.business;

import lombok.AllArgsConstructor;
import pl.cardealership.business.DAO.CustomerDAO;
import pl.cardealership.domain.CarServiceRequest;
import pl.cardealership.infrastructure.database.entity.AddressEntity;
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

    public void saveServiceRequest(CustomerEntity customer) {
        customerDAO.saveServiceRequest(customer);
    }

    public CustomerEntity saveCustomer(CarServiceRequest.Customer customer) {
        CustomerEntity entity = CustomerEntity.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .address(AddressEntity.builder()
                        .country(customer.getAddress().getCountry())
                        .city(customer.getAddress().getCity())
                        .postalCode(customer.getAddress().getPostalCode())
                        .address(customer.getAddress().getAddress())
                        .build())
                .build();

        return customerDAO.saveCustomer(entity);
    }
}
