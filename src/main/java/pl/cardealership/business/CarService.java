package pl.cardealership.business;

import lombok.AllArgsConstructor;
import pl.cardealership.business.DAO.CarDAO;
import pl.cardealership.infrastructure.database.entity.CarToBuyEntity;

import java.util.Optional;

@AllArgsConstructor
public class CarService {

    private final CarDAO carDAO;
    public CarToBuyEntity findCarToBuy(String vin) {
        final Optional<CarToBuyEntity> carToBuyByVin = carDAO.findCarToBuyByVin(vin);
        if (carToBuyByVin.isEmpty()) {
            throw new RuntimeException("Could not find Car by vin: [%s]".formatted(vin));
        }
        return carToBuyByVin.get();
    }
}
