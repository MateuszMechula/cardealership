package pl.cardealership.business;

import lombok.AllArgsConstructor;
import pl.cardealership.business.DAO.CarDAO;
import pl.cardealership.domain.CarServiceRequest;
import pl.cardealership.infrastructure.database.entity.CarToBuyEntity;
import pl.cardealership.infrastructure.database.entity.CarToServiceEntity;

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

    public Optional<CarToServiceEntity> findCarToService(String vin) {
        return carDAO.findCarToServiceByVin(vin);
    }

    public CarToServiceEntity saveCarToService(CarToBuyEntity carToBuy) {
        CarToServiceEntity carToService = CarToServiceEntity.builder()
                .vin(carToBuy.getVin())
                .brand(carToBuy.getBrand())
                .model(carToBuy.getModel())
                .year(carToBuy.getYear())
                .build();

        return carDAO.saveCarToService(carToService);
    }

    public CarToServiceEntity saveCarToService(CarServiceRequest.Car car) {
        CarToServiceEntity carToService = CarToServiceEntity.builder()
                .vin(car.getVin())
                .brand(car.getBrand())
                .model(car.getModel())
                .year(car.getYear())
                .build();

        return carDAO.saveCarToService(carToService);
    }
}
