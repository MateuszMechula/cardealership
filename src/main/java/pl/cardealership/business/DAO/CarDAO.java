package pl.cardealership.business.DAO;

import pl.cardealership.infrastructure.database.entity.CarToBuyEntity;
import pl.cardealership.infrastructure.database.entity.CarToServiceEntity;

import java.util.Optional;

public interface CarDAO {
    Optional<CarToServiceEntity> findCarToServiceByVin(String vin);

    Optional<CarToBuyEntity> findCarToBuyByVin(String vin);

    CarToServiceEntity saveCarToService(CarToServiceEntity carToService);
}
