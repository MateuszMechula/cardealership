package pl.cardealership.business.DAO;

import pl.cardealership.infrastructure.database.entity.CarToBuyEntity;

import java.util.Optional;

public interface CarDAO {
    Optional<CarToBuyEntity> findCarToBuyByVin(String vin);

}
