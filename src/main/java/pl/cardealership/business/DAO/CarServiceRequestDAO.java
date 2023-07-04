package pl.cardealership.business.DAO;

import pl.cardealership.domain.CarServiceRequest;

import java.util.Set;

public interface CarServiceRequestDAO {
    Set<CarServiceRequest> findActiveServiceRequestsByCarVin(String carVin);
}
