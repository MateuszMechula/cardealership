package pl.cardealership.business.DAO;

import pl.cardealership.infrastructure.database.entity.ServiceEntity;

import java.util.Optional;

public interface ServiceDAO {
    Optional<ServiceEntity> findByServiceCode(String serviceCode);

}
