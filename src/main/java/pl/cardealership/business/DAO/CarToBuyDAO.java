package pl.cardealership.business.DAO;

import pl.cardealership.domain.CarToBuy;

import java.util.Optional;

public interface CarToBuyDAO {

    Optional<CarToBuy> findCarToBuyByVin(String vin);
}
