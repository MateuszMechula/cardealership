package pl.cardealership.business.DAO;

import pl.cardealership.infrastructure.database.entity.CarServiceRequestEntity;

import java.util.Set;

public interface CarServiceRequestDAO {
    Set<CarServiceRequestEntity> findActiveServiceRequestsByCarVin(String carVin);
}
