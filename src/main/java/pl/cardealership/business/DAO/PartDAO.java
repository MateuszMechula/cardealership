package pl.cardealership.business.DAO;

import pl.cardealership.domain.Part;

import java.util.Optional;

public interface PartDAO {

    Optional<Part> findByPartCode(String serialNumber);
}
