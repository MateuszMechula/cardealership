package pl.cardealership.business;

import lombok.AllArgsConstructor;
import pl.cardealership.business.DAO.MechanicDAO;
import pl.cardealership.infrastructure.database.entity.MechanicEntity;

import java.util.Optional;

@AllArgsConstructor
public class MechanicService {

    private final MechanicDAO mechanicDAO;

    public MechanicEntity findMechanic(String pesel) {
        Optional<MechanicEntity> mechanic = mechanicDAO.findByPesel(pesel);
        if (mechanic.isEmpty()) {
            throw new RuntimeException("Could not find mechanic by pesel: [%s]".formatted(pesel));
        }
        return mechanic.get();
    }
}
