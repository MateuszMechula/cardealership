package pl.cardealership.business.DAO;

import pl.cardealership.domain.Service;

import java.util.Optional;

public interface ServiceDAO {
    Optional<Service> findByServiceCode(String serviceCode);

}
