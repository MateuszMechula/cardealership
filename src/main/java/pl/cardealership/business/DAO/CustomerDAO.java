package pl.cardealership.business.DAO;

import pl.cardealership.infrastructure.database.entity.CustomerEntity;

import java.util.Optional;

public interface CustomerDAO {

    Optional<CustomerEntity> findByEmail(String email);

    void issueInvoice(CustomerEntity customer);
}
