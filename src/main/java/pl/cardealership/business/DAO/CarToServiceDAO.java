package pl.cardealership.business.DAO;

import pl.cardealership.domain.CarHistory;
import pl.cardealership.domain.CarToService;

import java.util.Optional;

public interface CarToServiceDAO {
    Optional<CarToService> findCarToServiceByVin(String vin);

    CarToService saveCarToService(CarToService carToService);

    CarHistory findCarHistoryByVin(String vin);
}
